package com.example.trips

import android.util.Log

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
