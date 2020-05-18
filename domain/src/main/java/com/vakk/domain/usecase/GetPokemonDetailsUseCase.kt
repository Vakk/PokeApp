package com.vakk.domain.usecase

import com.vakk.domain.entity.PokemonDetails

interface GetPokemonDetailsUseCase {
    suspend operator fun invoke(pokemonId: Long): PokemonDetails?
}