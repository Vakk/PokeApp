package com.vakk.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vakk.pokemon_details.PokemonDetailsDao
import com.vakk.pokemon_details.PokemonDetailsDto
import com.vakk.sprites.SpritesDao
import com.vakk.sprites.SpritesDto

@Database(
    entities = [
        PokemonDetailsDto::class,
        SpritesDto::class
    ], version = 1
)
@TypeConverters(com.vakk.typeconverter.TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDetailsDao(): PokemonDetailsDao

    abstract fun spritesDao(): SpritesDao
}