package com.vakk.core.repository

import com.vakk.sprites.SpritesDao
import com.vakk.sprites.SpritesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonSpritesRepository(
    private val spritesDao: SpritesDao
) {
    suspend fun getByPokemonId(pokemonId: Long): SpritesDto? = withContext(Dispatchers.IO) {
        spritesDao.get(pokemonId)
    }

    suspend fun insertAll(sprites: List<SpritesDto>) = withContext(Dispatchers.IO) {
        spritesDao.insertAll(sprites)
    }
}