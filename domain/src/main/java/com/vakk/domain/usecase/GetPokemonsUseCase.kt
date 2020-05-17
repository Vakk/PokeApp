package com.vakk.domain.usecase

import com.vakk.domain.entity.Pokemon

interface GetPokemonsUseCase {
    suspend operator fun invoke(skip: Int, take: Int): List<Pokemon>
}