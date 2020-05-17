package com.vakk.core.usecase

import com.vakk.core.repository.PokemonsRepository
import com.vakk.domain.entity.Pokemon
import com.vakk.domain.usecase.GetPokemonsUseCase
import javax.inject.Inject

class GetPokemonsUseCaseImpl @Inject constructor(
    val pokemonsRepository: PokemonsRepository
) : GetPokemonsUseCase {
    override suspend fun invoke(skip: Int, take: Int): List<Pokemon> {
        return pokemonsRepository.getPokemons(skip, take)
    }
}