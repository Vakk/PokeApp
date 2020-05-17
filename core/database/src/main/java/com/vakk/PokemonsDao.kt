package com.vakk

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonsDao {
    @Query("SELECT * FROM PokemonDto LIMIT :take OFFSET :skip")
    fun getAll(skip: Int, take: Int): List<PokemonDto>

    @Insert(entity = PokemonDto::class, onConflict = OnConflictStrategy.REPLACE)
    fun replaceAll(entities: List<PokemonDto>)

    @Insert(entity = PokemonDto::class, onConflict = OnConflictStrategy.REPLACE)
    fun replace(entities: PokemonDto)

    @Query("DELETE FROM PokemonDto")
    fun clear()
}