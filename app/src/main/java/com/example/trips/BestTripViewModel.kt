package com.example.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BestTripViewModel: ViewModel() {
    private val repository = TripRepository()

    fun search() {
        viewModelScope.launch {
            repository.sync()
        }
    }
}