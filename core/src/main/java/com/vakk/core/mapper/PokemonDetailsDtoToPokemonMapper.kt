package com.vakk.core.mapper

import com.vakk.core.repository.PokemonSpritesRepository
import com.vakk.domain.entity.Pokemon
import com.vakk.pokemon_details.PokemonDetailsDto
import javax.inject.Inject

class PokemonDetailsDtoToPokemonMapper @Inject constructor(
    val spritesRepository: PokemonSpritesRepository
) {
    suspend operator fun invoke(
        pokemonDetails: List<PokemonDetailsDto>
    ): List<Pokemon> {
        return pokemonDetails.map {
            Pokemon(
                id = it.id,
                name = it.name,
                iconUrl = spritesRepository.getByPokemonId(pokemonId = it.id)?.frontDefault ?: ""
            )
        }
    }
}