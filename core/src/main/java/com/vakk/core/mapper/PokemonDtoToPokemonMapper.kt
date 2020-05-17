package com.vakk.core.mapper

import com.vakk.PokemonDto
import com.vakk.domain.entity.Pokemon

class PokemonDtoToPokemonMapper {
    operator fun invoke(pokemon: PokemonDto): Pokemon {
        return Pokemon(
            name = pokemon.name,
            iconUrl = pokemon.imageUrl
        )
    }
}