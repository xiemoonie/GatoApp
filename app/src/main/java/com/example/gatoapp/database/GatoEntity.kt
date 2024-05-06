package com.example.gatoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GatoEntity(
    @ColumnInfo val fact: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
}