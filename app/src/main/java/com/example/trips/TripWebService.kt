package com.example.trips

import android.util.Log

private const val TAG = "BestTripWebService"

class TripWebService {

    private val bestTripService: BestTripsApi =
        InitializatorRetrofit().bestTripApi


    suspend fun search(){
        try {
            val friendsReturn = bestTripService
                .search()
            Log.i("testeSearch", "search: ${friendsReturn.data.size}")

        } catch (e: Exception) {
            Log.e(TAG, "searchAll: ", e)
        }
    }
}
