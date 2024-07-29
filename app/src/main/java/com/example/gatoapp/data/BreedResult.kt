package com.example.gatoapp.data

import com.google.gson.annotations.SerializedName

data class BreedResult(
  @SerializedName("data")
    var data : List<Data>
)
data class Data(
    @SerializedName("breed")
    var breed : String,
    @SerializedName("country")
    var country: String,
    @SerializedName("origin")
    var origin: String,
    @SerializedName("coat")
    var coat: String,
    @SerializedName("pattern")
    var pattern: String
)