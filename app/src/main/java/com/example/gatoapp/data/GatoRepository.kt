package com.example.gatoapp.data

import com.example.gatoapp.database.GatoDao
import com.example.gatoapp.database.GatoEntity
import kotlinx.coroutines.flow.Flow

class GatoRepository(val gatoAPI: GatoAPI, val gatoDao: GatoDao) {
    suspend fun getAll(): Flow<List<GatoEntity>> {
        return gatoDao.getAll()
    }

    suspend fun getGato(): String? {
        val gato = gatoAPI.getGato()
        return gato.body()?.fact
    }

    suspend fun insert() {
        val gato = GatoEntity(getGato() ?: throw Exception())
        gatoDao.insert(gato)
    }
}