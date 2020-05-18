package com.vakk.core.usecase

import com.vakk.core.mapper.PokemonDetailsDtoToPokemonMapper
import com.vakk.core.mapper.SpriteDtoToSpriteMapper
import com.vakk.core.repository.PokemonSpritesRepository
import com.vakk.core.repository.PokemonsRepository
import com.vakk.domain.entity.Ability
import com.vakk.domain.entity.PokemonDetails
import com.vakk.domain.usecase.GetPokemonDetailsUseCase
import javax.inject.Inject

class GetPokemonDetailsUseCaseImpl @Inject constructor(
    val pokemonsRepository: PokemonsRepository,
    val spritesRepository: PokemonSpritesRepository,
    val pokemonMapper: PokemonDetailsDtoToPokemonMapper,
    val spriteDtoMapper: SpriteDtoToSpriteMapper
) : GetPokemonDetailsUseCase {
    override suspend fun invoke(pokemonId: Long): PokemonDetails? {
        val item = pokemonsRepository.getById(pokemonId) ?: return null
        val sprites = spritesRepository.getByPokemonId(item.id) ?: return null
        return PokemonDetails(
            pokemon = pokemonMapper(item),
            sprites = spriteDtoMapper(sprites),
            forms = emptyList(),
            abilities = item.abilities.map { Ability(-1, it) }
        )
    }
}