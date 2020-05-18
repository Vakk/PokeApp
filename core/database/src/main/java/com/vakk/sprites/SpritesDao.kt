package com.vakk.sprites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpritesDao {
    @Query("SELECT * FROM SpritesDto LIMIT :take OFFSET :skip")
    fun getAll(skip: Int, take: Int): List<SpritesDto>

    @Query("SELECT * FROM SpritesDto WHERE pokemonId IN (:pokemonIdsList) LIMIT :take OFFSET :skip")
    fun getAll(pokemonIdsList: List<Long>, skip: Int, take: Int): List<SpritesDto>

    @Query("SELECT * FROM SpritesDto WHERE pokemonId == :pokemonId ")
    fun get(pokemonId: Long): SpritesDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<SpritesDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(entity: SpritesDto)

    @Query("DELETE FROM SpritesDto")
    fun clear()
}