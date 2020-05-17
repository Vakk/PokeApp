package com.vakk.network.service

import com.vakk.network.bean.GetPokemonInfoBean
import com.vakk.network.bean.GetPokemonsBean
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonsApiService {
    @GET("v2/pokemon/")
    fun getPokemonsProtoList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): GetPokemonsBean

    @GET
    fun getPokemonProto(
        @Url pokemonInfoUrl: String
    ): GetPokemonInfoBean
}