package com.example.trips.repository

import androidx.lifecycle.MutableLiveData
import com.example.trips.Trips
import com.example.trips.data.entites.Trip
import com.example.trips.data.firebase.TripFirebaseService
import com.example.trips.data.api.TripWebService
import com.example.trips.today

class TripRepository {
    private val webClient = TripWebService()
    private val firebase = TripFirebaseService()
    private var listTrips = mutableListOf<Trips>()
    private var listOldTrips = MutableLiveData<List<Trip>>()

    fun searchTripsFirebase() {
        listOldTrips.value = firebase.searchFirebaseData()
    }

    fun getOldTrips(): MutableList<Trip> {
        val list = mutableListOf<Trip>()
        for (j in listOldTrips.value?.indices!!) {
            if(listOldTrips.value?.get(j)!!.date!= today()){
                list.add(listOldTrips.value?.get(j)!!)
            }
        }
        return list
    }
    fun getTodayTrips(): MutableList<Trip> {
        val list = mutableListOf<Trip>()
        for (j in listOldTrips.value?.indices!!) {
            if(listOldTrips.value?.get(j)!!.date== today()){
                list.add(listOldTrips.value?.get(j)!!)
            }
        }
        return list
    }
    suspend fun sync(): List<Trips>? {
        val list = webClient.searchApi()
        if (list != null) {
            for (i in 0 until 5) {
                if(tripIsToday(list[i].id)){
                        if(!listTrips.contains(list[i])) listTrips.add(list[i])
                }else if (!tripWasYesterday(list[i].id)) {
                        firebase.sendTripToFirebase(Trip(id_trip = list[i].id, date = today()))
                        if(!listTrips.contains(list[i])) listTrips.add(list[i])
                }
            }
        }
        return listTrips
    }

    private fun tripWasYesterday(id_trip: String): Boolean {
        var returnV = false
        for (j in getOldTrips().indices!!) {
            if (getOldTrips()[j]?.id_trip == id_trip) {
                returnV = true
            }
        }
        return returnV
    }
    private fun tripIsToday(id_trip: String): Boolean {
        var returnV = false
        for (j in getTodayTrips().indices!!) {
            if (getTodayTrips()[j]?.id_trip == id_trip) {
                returnV = true
            }
        }
        return returnV
    }
}
