package com.vakk

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PokemonDto(
    @PrimaryKey
    var id: Long,
    var name: String,
    var imageUrl: String
)