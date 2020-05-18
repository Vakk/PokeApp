package com.vakk.core.mapper

import com.vakk.network.bean.GetPokemonInfoBean
import com.vakk.sprites.SpritesDto
import javax.inject.Inject

class SpriteBeanToSpriteDtoMapper @Inject constructor() {
    operator fun invoke(pokemonId: Long, sprites: GetPokemonInfoBean.Sprites): SpritesDto? {
        return SpritesDto(
            pokemonId = pokemonId,
            backDefault = sprites.backDefault,
            backFemale = sprites.backFemale,
            backShiny = sprites.backShiny,
            backShinyFemale = sprites.backShinyFemale,
            frontDefault = sprites.frontDefault,
            frontFemale = sprites.frontFemale,
            frontShiny = sprites.frontShiny,
            frontShinyFemale = sprites.frontShinyFemale
        )
    }
}