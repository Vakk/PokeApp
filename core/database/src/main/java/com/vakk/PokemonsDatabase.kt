package com.vakk

class PokemonsDatabase(
    val pokemonsDao: PokemonsDao
) {
    fun getPokemons(
        skip: Int,
        take: Int
    ): List<PokemonDto> {
        return pokemonsDao.getAll(skip, take)
    }

    fun savePokemons(pokemons: List<PokemonDto>) {
        pokemonsDao.replaceAll(pokemons)
    }

    fun deleteAll() {
        pokemonsDao.clear()
    }
}