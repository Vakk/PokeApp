package com.vakk.network.datasource

import com.vakk.network.bean.GetPokemonInfoBean
import com.vakk.network.bean.GetPokemonsBean
import com.vakk.network.service.PokemonsApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokeApiDatasource(
    val pokemonsApiService: PokemonsApiService,
    val dispatcher: CoroutineDispatcher
) {
    suspend fun getPokemonsProtoList(limit: Int, offset: Int): GetPokemonsBean =
        withContext(dispatcher) {
            pokemonsApiService.getPokemonsProtoList(
                limit = limit,
                offset = offset
            )
        }

    suspend fun getPokemonProto(url: String): GetPokemonInfoBean = withContext(dispatcher) {
        pokemonsApiService.getPokemonProto(url)
    }
}