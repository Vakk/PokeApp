package com.vakk.domain.entity

data class Form(
    val id: Long,
    val name: String,
    val sprites: List<Sprites>,
    val isBattleOnly: Boolean,
    val isDefault: Boolean
)