package com.example.trips

class TripRepository {
    private val webClient = TripWebService()

    suspend fun sync() {
        webClient.search()
    }

}
