package com.example.gatoapp.data

import com.google.gson.annotations.SerializedName

data class GatoResult(
    @SerializedName("fact")
    val fact: String,
    @SerializedName("length")
    val length: Int
) {

}