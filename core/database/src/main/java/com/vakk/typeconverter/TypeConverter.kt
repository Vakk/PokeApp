package com.vakk.typeconverter

import androidx.room.TypeConverter

class TypeConverter {
    @TypeConverter
    fun fromList(value: List<String>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return value.split(",")
    }
}