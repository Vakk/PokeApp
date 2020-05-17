package com.vakk

import androidx.room.Entity

@Entity
class PokemonDto(
    var name: String,
    var imageUrl: String
)