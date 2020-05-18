package com.vakk.core.usecase

import com.vakk.core.mapper.PokemonDetailsDtoToPokemonMapper
import com.vakk.core.mapper.SpriteBeanToSpriteDtoMapper
import com.vakk.core.repository.PokemonSpritesRepository
import com.vakk.core.repository.PokemonsRepository
import com.vakk.domain.entity.Pokemon
import com.vakk.domain.usecase.GetPokemonsUseCase
import javax.inject.Inject

class GetPokemonsUseCaseImpl @Inject constructor(
    private val pokemonsRepository: PokemonsRepository,
    private val spritesRepository: PokemonSpritesRepository,
    private val pokemonDtoToPokemonMapper: PokemonDetailsDtoToPokemonMapper,
    private val spriteBeanToSpriteDtoMapper: SpriteBeanToSpriteDtoMapper
) : GetPokemonsUseCase {
    override suspend fun invoke(skip: Int, take: Int): List<Pokemon> {
        // checking cache, if it doesn't have required items - load from remote datasource.
        val localItems = pokemonsRepository.getLocalData(skip, take)
        return if (localItems.size < take) {
            fetchRemoteData(skip, take)
            // we've fetched data, so try to load items from DB.
            pokemonDtoToPokemonMapper(pokemonsRepository.getLocalData(skip, take))
        } else {
            pokemonDtoToPokemonMapper(localItems)
        }.apply {
            forEach {
                it.iconUrl = spritesRepository.getByPokemonId(it.id)?.frontDefault ?: ""
            }
        }
    }

    private suspend fun fetchRemoteData(skip: Int, take: Int) {
        val beans = pokemonsRepository.fetchRemoteData(skip, take)
        val sprites = beans.mapNotNull { spriteBeanToSpriteDtoMapper(it.id, it.sprites) }
        spritesRepository.insertAll(sprites)
    }
}