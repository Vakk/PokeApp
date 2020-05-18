package com.vakk.domain.entity

data class Language(
    val id: Long,
    val iso3166: String,
    val iso639: String,
    val name: String
)