package com.example.gatoapp.database

import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.room.util.query
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [GatoEntity::class, EntityBreed::class ], version = 2)
abstract class DataBase : RoomDatabase() {
    abstract fun gatoDao(): GatoDao
    abstract fun breedDao() : BreedDao

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `EntityBreed` (`breed` TEXT NOT NULL, `country` TEXT NOT NULL, `origin` TEXT NOT NULL, `coat` TEXT NOT NULL, `pattern` TEXT NOT NULL, PRIMARY KEY(`breed`))")
    }
}
