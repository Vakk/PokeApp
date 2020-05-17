package com.vakk.network.datasource

import com.vakk.network.common.extensions.ApiResult
import com.vakk.network.bean.GetPokemonInfoBean
import com.vakk.network.bean.GetPokemonsBean
import com.vakk.network.service.PokemonsApiService
import com.vakk.network.common.extensions.toApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokeApiDatasource(
    val pokemonsApiService: PokemonsApiService,
    val dispatcher: CoroutineDispatcher
) {
    suspend fun getPokemonsProtoList(limit: Int, offset: Int): ApiResult<GetPokemonsBean> =
        withContext(dispatcher) {
            pokemonsApiService.getPokemonsProtoList(
                limit = limit,
                offset = offset
            ).toApiResult()
        }

    suspend fun getPokemonProto(url: String): ApiResult<GetPokemonInfoBean> =
        withContext(dispatcher) {
            pokemonsApiService
                .getPokemonProto(url)
                .toApiResult()
        }
}