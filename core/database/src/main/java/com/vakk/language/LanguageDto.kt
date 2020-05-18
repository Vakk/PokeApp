package com.vakk.language

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LanguageDto(
    @PrimaryKey
    var id: Long,
    val iso3166: String,
    val iso639: String,
    val name: String
)