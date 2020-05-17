package com.vakk.core.usecase

import com.vakk.core.mapper.PokemonDtoToPokemonMapper
import com.vakk.core.repository.PokemonsRepository
import com.vakk.domain.entity.Pokemon
import com.vakk.domain.usecase.GetPokemonsUseCase
import javax.inject.Inject

class GetPokemonsUseCaseImpl @Inject constructor(
    private val pokemonsRepository: PokemonsRepository,
    private val pokemonDtoToPokemonMapper: PokemonDtoToPokemonMapper
) : GetPokemonsUseCase {
    override suspend fun invoke(skip: Int, take: Int): List<Pokemon> {
        // checking cache, if it doesn't have required items - load from remote datasource.
        val localItems = pokemonsRepository.getLocalData(skip, take)
        if (localItems.size < take) {
            pokemonsRepository.fetchRemoteData(skip, take)
        }
        // we've fetched data, so try to load items from DB.
        return pokemonsRepository.getLocalData(skip, take).map {
            pokemonDtoToPokemonMapper(it)
        }
    }
}