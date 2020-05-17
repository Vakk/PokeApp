package com.vakk.core.usecase

import com.vakk.domain.entity.Pokemon
import com.vakk.domain.usecase.GetPokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonsUseCaseImpl @Inject constructor() : GetPokemonsUseCase {
    override suspend fun invoke(skip: Int, take: Int): List<Pokemon> =
        withContext(Dispatchers.Default) {
            listOf(
                Pokemon(
                    "Pokemon 1",
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/046.png"
                ),
                Pokemon(
                    "Pokemon 1",
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/046.png"
                ),
                Pokemon(
                    "Pokemon 1",
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/046.png"
                ),
                Pokemon(
                    "Pokemon 1",
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/046.png"
                ),
                Pokemon(
                    "Pokemon 1",
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/046.png"
                ),
                Pokemon(
                    "Pokemon 1",
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/046.png"
                ),
                Pokemon(
                    "Pokemon 2",
                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/835.png"
                )
            )
        }
}