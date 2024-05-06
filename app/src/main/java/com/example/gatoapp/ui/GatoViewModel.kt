package com.example.gatoapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatoapp.data.GatoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class GatoViewModel(val repository: GatoRepository) : ViewModel() {
    var index = MutableStateFlow(0)
    val fact = mutableStateOf("Loading...")
    fun getPrevious() {
        if (index.value > 0) {
            index.value -= 1
        }
    }

    fun getNext() {
        index.value += 1
    }

    fun getCatFact() {
        viewModelScope.launch(Dispatchers.IO) {
            fact.value = "Loading..."
            repository.getAll().combine(index) { catfacts, index ->
                if (index < catfacts.size) {
                    fact.value = catfacts[index].fact
                } else {
                    try {
                        repository.insert()
                    } catch (e: Exception) {
                        fact.value = "Error" + e.localizedMessage
                        e.printStackTrace()
                    }
                }
            }.collect {
            }
        }
    }
}