package com.vakk.pokemon_details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDetailsDao {
    @Query("SELECT * FROM PokemonDetailsDto where id == :id")
    fun getItemById(id: Long): PokemonDetailsDto?

    @Query("SELECT * FROM PokemonDetailsDto LIMIT :take OFFSET :skip")
    fun getAll(skip: Int, take: Int): List<PokemonDetailsDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<PokemonDetailsDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(entity: PokemonDetailsDto)

    @Query("DELETE FROM PokemonDetailsDto")
    fun clear()
}