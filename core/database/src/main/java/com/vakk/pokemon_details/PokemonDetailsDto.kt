package com.vakk.pokemon_details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PokemonDetailsDto(
    @PrimaryKey
    val id: Long,
    val abilities: List<String>,
    val baseExperience: Int,
    val forms: List<String>,
    val gameIndices: List<String>,
    val height: Int,
    val heldItems: List<String>,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<String>,
    val name: String,
    val order: Int,
    val species: String,
    val sprites: Long,
    val stats: List<String>,
    val types: List<String>,
    val weight: Int
)