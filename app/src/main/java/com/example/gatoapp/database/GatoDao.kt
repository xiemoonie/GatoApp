package com.example.gatoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GatoDao {
    @Query("SELECT * FROM GatoEntity")
    fun getAll(): Flow<List<GatoEntity>>

    @Insert
    fun insert(vararg gatoEntity: GatoEntity)
}