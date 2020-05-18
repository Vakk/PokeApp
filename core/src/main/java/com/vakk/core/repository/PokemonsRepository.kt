package com.vakk.core.repository

import com.vakk.core.mapper.GetPokemonInfoBeanListToPokemonDtoMapper
import com.vakk.network.common.extensions.getResultOrThrowError
import com.vakk.network.datasource.PokeApiDatasource
import com.vakk.pokemon_details.PokemonDetailsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class PokemonsRepository(
    private val database: PokemonDetailsDao,
    private val datasource: PokeApiDatasource,
    private val dispatcher: CoroutineDispatcher,
    private val getPokemonInfoBeanListToPokemonDtoMapper: GetPokemonInfoBeanListToPokemonDtoMapper
) {

    suspend fun getById(id: Long) = withContext(dispatcher) {
        database.getItemById(id)
    }

    suspend fun fetchRemoteData(skip: Int, take: Int) = withContext(dispatcher) {
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
        val result = pokemons.map {
            it.await().getResultOrThrowError()
        }
        getPokemonInfoBeanListToPokemonDtoMapper.invoke(result).apply {
            database.insertAll(this)
        }
        result
    }

    suspend fun getLocalData(skip: Int, take: Int) = withContext(dispatcher) {
        database.getAll(skip = skip, take = take)
    }
}
