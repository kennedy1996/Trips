package com.example.trips.data.api

import android.util.Log
import com.example.trips.BestTripData
import com.example.trips.inicializator.InitializatorRetrofit

private const val TAG = "BestTripWebService"

class TripWebService {

    private val bestTripService: BestTripsApi =
        InitializatorRetrofit().bestTripApi

    suspend fun searchApi(): List<BestTripData>? {
        var returnV: List<BestTripData>? = null
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
