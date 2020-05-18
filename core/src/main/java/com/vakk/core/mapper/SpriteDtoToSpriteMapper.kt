package com.vakk.core.mapper

import com.vakk.domain.entity.Sprites
import com.vakk.sprites.SpritesDto
import javax.inject.Inject

class SpriteDtoToSpriteMapper @Inject constructor() {
    operator fun invoke(dto: SpritesDto): Sprites {
        return Sprites(
            backShinyFemale = dto.backShinyFemale,
            frontShinyFemale = dto.frontShinyFemale,
            frontShiny = dto.frontShiny,
            frontFemale = dto.frontFemale,
            frontDefault = dto.frontDefault,
            backShiny = dto.backShiny,
            backFemale = dto.backFemale,
            backDefault = dto.backDefault
        )
    }
}