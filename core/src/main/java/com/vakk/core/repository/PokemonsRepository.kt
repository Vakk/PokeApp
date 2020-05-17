package com.vakk.core.repository

import com.vakk.domain.entity.Pokemon
import com.vakk.network.common.extensions.getResultOrThrowError
import com.vakk.network.datasource.PokeApiDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

interface PokemonsRepository {
    suspend fun getPokemons(skip: Int, take: Int): List<Pokemon>
}

class PokemonsRepositoryImpl(
    private val datasource: PokeApiDatasource,
    private val dispatcher: CoroutineDispatcher
) : PokemonsRepository {

    override suspend fun getPokemons(skip: Int, take: Int): List<Pokemon> =
        withContext(dispatcher) {
            val bean = datasource.getPokemonsProtoList(
                limit = take,
                offset = skip
            ).getResultOrThrowError()
            val pokemons =
                bean.results.mapNotNull {
                    it.url?.let {
                        async {
                            datasource.getPokemonProto(it)
                        }
                    }
                }
            pokemons.map {
                val bean = it.await().getResultOrThrowError()
                Pokemon(
                    bean.name,
                    bean.sprites.frontDefault ?: ""
                )
            }
        }

}