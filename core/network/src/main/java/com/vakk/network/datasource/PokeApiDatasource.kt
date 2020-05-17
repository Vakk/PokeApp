package com.vakk.network.datasource

import com.vakk.network.bean.GetPokemonInfoBean
import com.vakk.network.bean.GetPokemonsBean
import com.vakk.network.common.extensions.ApiResult
import com.vakk.network.common.extensions.toApiResult
import com.vakk.network.service.PokemonsApiService

class PokeApiDatasource(
    val pokemonsApiService: PokemonsApiService
) {
    fun getPokemonsProtoList(limit: Int, offset: Int): ApiResult<GetPokemonsBean> =
        pokemonsApiService.getPokemonsProtoList(
            limit = limit,
            offset = offset
        ).toApiResult()


    fun getPokemonProto(url: String): ApiResult<GetPokemonInfoBean> = pokemonsApiService
        .getPokemonProto(url)
        .toApiResult()

}