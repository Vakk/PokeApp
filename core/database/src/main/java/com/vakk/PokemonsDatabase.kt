package com.vakk

class PokemonsDatabase(
    val pokemonsDao: PokemonsDao
) {
    fun getAll(
        skip: Int,
        take: Int
    ): List<PokemonDto> {
        return pokemonsDao.getAll(skip, take)
    }

    fun insertAll(pokemons: List<PokemonDto>) {
        pokemonsDao.insertAll(pokemons)
    }

    fun deleteAll() {
        pokemonsDao.clear()
    }
}