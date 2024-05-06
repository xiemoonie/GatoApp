package com.example.gatoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GatoEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun gatoDao(): GatoDao
}