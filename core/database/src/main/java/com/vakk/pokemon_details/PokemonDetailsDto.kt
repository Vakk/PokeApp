package com.vakk.pokemon_details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PokemonDetailsDto(
    @PrimaryKey
    var id: Long,
    var name: String,
    var imageUrl: String
)