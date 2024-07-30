package com.example.gatoapp.data

import com.example.gatoapp.database.BreedDao
import com.example.gatoapp.database.EntityBreed
import com.example.gatoapp.database.GatoDao
import com.example.gatoapp.database.GatoEntity
import kotlinx.coroutines.flow.Flow

class GatoRepository(val gatoAPI: GatoAPI, val gatoDao: GatoDao, val breedDao: BreedDao) {
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

    suspend fun insertBreedsToDataBase(){
        val breed = gatoAPI.getBreads().body()?.data?:throw IllegalStateException()
        breedDao.insertBreed(breed.map { EntityBreed( breed = it.breed, country = it.country, origin = it.origin, coat = it.coat, pattern = it.pattern) })
    }

    fun getBreed() : Flow<List<EntityBreed>>{
        return breedDao.getBreed()
    }
    fun eraseBreedsFromDataBase(breed : String){
          breedDao.deleteBreed(breed)
    }


}