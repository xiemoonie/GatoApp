package com.example.gatoapp.data

import retrofit2.Response
import retrofit2.http.GET

interface GatoAPI {
    @GET("fact")
    suspend fun getGato(): Response<GatoResult>
}