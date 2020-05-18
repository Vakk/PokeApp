package com.vakk.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vakk.language.LanguageDao
import com.vakk.language.LanguageDto
import com.vakk.pokemon_details.PokemonDetailsDao
import com.vakk.pokemon_details.PokemonDetailsDto
import com.vakk.sprites.SpritesDao
import com.vakk.sprites.SpritesDto

@Database(
    entities = [
        PokemonDetailsDto::class,
        SpritesDto::class,
        LanguageDto::class
    ], version = 1
)
@TypeConverters(com.vakk.typeconverter.TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDetailsDao(): PokemonDetailsDao

    abstract fun spritesDao(): SpritesDao

    abstract fun languageDao(): LanguageDao
}