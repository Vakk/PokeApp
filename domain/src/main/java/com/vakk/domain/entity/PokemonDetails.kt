package com.vakk.domain.entity

data class PokemonDetails(
    val pokemon: Pokemon,
    val sprites: Sprites,
    val abilities: List<Ability>,
    val forms: List<Form>
)