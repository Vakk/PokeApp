package com.vakk.core.repository

import com.vakk.PokemonDto
import com.vakk.PokemonsDatabase
import com.vakk.core.mapper.PokemonDtoToPokemonMapper
import com.vakk.core.mapper.PokemonToPokemonDtoMapper
import com.vakk.domain.entity.Pokemon
import com.vakk.network.common.extensions.getResultOrThrowError
import com.vakk.network.datasource.PokeApiDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

interface PokemonsRepository {
    suspend fun getPokemons(skip: Int, take: Int): List<Pokemon>
}

class PokemonsRepositoryImpl(
    private val database: PokemonsDatabase,
    private val datasource: PokeApiDatasource,
    private val dispatcher: CoroutineDispatcher,
    private val pokemonDtoToPokemonMapper: PokemonDtoToPokemonMapper,
    private val pokemonToPokemonDtoMapper: PokemonToPokemonDtoMapper
) : PokemonsRepository {

    override suspend fun getPokemons(skip: Int, take: Int): List<Pokemon> =
        withContext(dispatcher) {
            // Try to load cached items.
            val localData = getCacheItems(skip, take)
            if (localData.size >= take) {
                return@withContext localData.map { pokemonDtoToPokemonMapper(it) }
            }

            // In case of db items list is smaller than take value - we should load it from API.
            getApiItems(skip, take).apply {
                map {
                    pokemonToPokemonDtoMapper(it)
                }.let {
                    database.insertAll(it) // save into cache to faster access next time.
                }
            }

        }

    private fun getCacheItems(skip: Int, take: Int): List<PokemonDto> {
        return database.getAll(skip = skip, take = take)
    }

    private suspend fun getApiItems(skip: Int, take: Int) = withContext(dispatcher) {
        val bean = datasource.getPokemonsProtoList(
            limit = take,
            offset = skip
        ).getResultOrThrowError()

        val pokemons = bean.results.mapNotNull {
            it.url?.let {
                async(Dispatchers.Default) {
                    datasource.getPokemonProto(it)
                }
            }
        }
        pokemons.map {
            val bean = it.await().getResultOrThrowError()
            Pokemon(
                id = bean.id,
                name = bean.name,
                iconUrl = bean.sprites.frontDefault ?: ""
            )
        }
    }
}
