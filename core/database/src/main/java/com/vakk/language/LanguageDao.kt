package com.vakk.language

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LanguageDao {
    @Query("SELECT * FROM LanguageDto where id == :id")
    fun getItemById(id: Long): LanguageDto?

    @Query("SELECT * FROM LanguageDto LIMIT :take OFFSET :skip")
    fun getAll(skip: Int, take: Int): List<LanguageDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<LanguageDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(entity: LanguageDto)

    @Query("DELETE FROM LanguageDto")
    fun clear()
}