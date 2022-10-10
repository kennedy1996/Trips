package com.example.trips.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trips.Trips
import com.example.trips.repository.TripRepository
import kotlinx.coroutines.launch

class BestTripViewModel : ViewModel() {
    private val repository = TripRepository()
    private var listTrips = MutableLiveData<List<Trips>>()

    fun search() {
        viewModelScope.launch {
            listTrips.value = repository.sync()
        }
    }
    fun getSearch(): MutableLiveData<List<Trips>> {
        return listTrips
    }

    fun searchOldTrips() {
        viewModelScope.launch {
            repository.searchTripsFirebase()
        }
    }
}