package com.vakk.core.mapper

import com.vakk.PokemonDto
import com.vakk.domain.entity.Pokemon
import javax.inject.Inject

class PokemonToPokemonDtoMapper @Inject constructor() {
    operator fun invoke(pokemon: Pokemon): PokemonDto {
        return PokemonDto(
            id = pokemon.id,
            name = pokemon.name,
            imageUrl = pokemon.iconUrl
        )
    }
}