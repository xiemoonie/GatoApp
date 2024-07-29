package com.example.gatoapp.data

import retrofit2.Response
import retrofit2.http.GET
import java.text.BreakIterator

interface GatoAPI {
    @GET("fact")
    suspend fun getGato(): Response<GatoResult>
    @GET("breeds")
    suspend fun getBreads(): Response<BreedResult>
}