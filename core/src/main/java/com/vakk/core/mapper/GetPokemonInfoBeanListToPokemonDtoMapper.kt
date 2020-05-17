package com.vakk.core.mapper

import com.vakk.PokemonDto
import com.vakk.network.bean.GetPokemonInfoBean
import javax.inject.Inject

class GetPokemonInfoBeanListToPokemonDtoMapper @Inject constructor() {
    operator fun invoke(beans: List<GetPokemonInfoBean>): List<PokemonDto> {
        return beans.map {
            PokemonDto(
                id = it.id,
                name = it.name,
                imageUrl = it.sprites.frontDefault ?: ""
            )
        }
    }
}