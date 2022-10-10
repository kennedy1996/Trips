package com.example.trips

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InitializatorRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.skypicker.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val bestTripApi = retrofit.create(BestTripsApi::class.java)

}
