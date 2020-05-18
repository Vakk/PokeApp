package com.vakk.sprites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SpritesDto(
    @PrimaryKey
    val pokemonId: Long,
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)