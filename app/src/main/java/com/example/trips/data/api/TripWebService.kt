package com.example.trips.data.api

import android.util.Log
import com.example.trips.Trips
import com.example.trips.inicializator.InitializatorRetrofit

private const val TAG = "BestTripWebService"

class TripWebService {

    private val bestTripService: BestTripsApi =
        InitializatorRetrofit().bestTripApi

    suspend fun searchApi(): List<Trips>? {
        var returnV: List<Trips>? = null
        try {
            val friendsReturn = bestTripService
                .search()
            returnV = friendsReturn.data

        } catch (e: Exception) {
            Log.e(TAG, "searchApi: ", e)
        }
        return returnV
    }
}
