package com.example.gatoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class EntityBreed(
    @PrimaryKey
    @ColumnInfo var breed : String,
    @ColumnInfo var country: String,
    @ColumnInfo var origin: String,
    @ColumnInfo var coat: String,
    @ColumnInfo var pattern: String)