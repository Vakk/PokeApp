package com.vakk.core.mapper

import com.vakk.PokemonDto
import com.vakk.domain.entity.Pokemon
import javax.inject.Inject

class PokemonDtoToPokemonMapper @Inject constructor() {
    operator fun invoke(pokemon: PokemonDto): Pokemon {
        return Pokemon(
            id = pokemon.id,
            name = pokemon.name,
            iconUrl = pokemon.imageUrl
        )
    }
}