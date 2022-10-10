package com.example.trips

class BestTripReturn (
    val search_id: String,
    val data: List<BestTripData>

)

class BestTripData (
    val id: String,
    val flyFrom: String
)
class Trips (
    val id: String
)