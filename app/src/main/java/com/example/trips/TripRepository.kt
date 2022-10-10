package com.example.trips

import androidx.lifecycle.MutableLiveData

class TripRepository {
    private val webClient = TripWebService()
    private val firebase = TripFirebaseService()
    private var listTrips = mutableListOf<BestTripData>()
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

    suspend fun sync(): List<BestTripData> {
        val list = webClient.searchApi()
        var five = 0
        if (list != null) {
            for (i in list.indices) {
                if(tripIsToday(list[i].id)){
                    if (five < 5) {
                        five += 1
                    listTrips.add(list[i])
                    }
                }else if (!tripWasYesterday(list[i].id)) {
                    if (five < 5) {
                        five += 1
                        firebase.sendTripToFirebase(Trip(id_trip = list[i].id, date = today()))
                        listTrips.add(list[i])
                    }
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
