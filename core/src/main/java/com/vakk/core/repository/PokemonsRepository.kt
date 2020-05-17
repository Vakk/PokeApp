package com.vakk.core.repository

import com.vakk.domain.entity.Pokemon

interface PokemonsRepository {
    fun getPokemons(skip: Int, take: Int): List<Pokemon>
}

class PokemonsRepositoryImpl(

) : PokemonsRepository {
    override fun getPokemons(skip: Int, take: Int): List<Pokemon> {

    }
}