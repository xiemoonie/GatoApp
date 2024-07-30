package com.example.gatoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {
    @Query("SELECT * FROM EntityBreed")
    fun getBreed() : Flow<List<EntityBreed>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreed( breedEntityBreed: List<EntityBreed>)
    @Query("DELETE FROM EntityBreed WHERE breed = :breed")
    fun deleteBreed(breed : String)
}