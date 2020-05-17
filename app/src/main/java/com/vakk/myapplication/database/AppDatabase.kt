package com.vakk.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vakk.PokemonDto
import com.vakk.PokemonsDao

@Database(entities = [PokemonDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonsDao(): PokemonsDao
}