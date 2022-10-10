package com.example.trips.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trips.data.entites.Trip
import com.example.trips.repository.TripRepository
import kotlinx.coroutines.launch

class BestTripViewModel: ViewModel() {
    private val repository = TripRepository()
    private var listTrips = MutableLiveData<List<Trip>>()

    fun search() {
        viewModelScope.launch {
            repository.sync()
        }
    }
    fun searchOldTrips() {
        viewModelScope.launch {
            repository.searchTripsFirebase()
        }
    }
}