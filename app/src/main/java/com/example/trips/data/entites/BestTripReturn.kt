package com.example.trips

class BestTripReturn (
    val search_id: String,
    val data: List<Trips>

)

class Trips (
    val id: String,
    val cityFrom: String,
    val cityTo: String,
    val countryFrom: CodeName,
    val countryTo: CodeName,
    val fly_duration: String,
    val price: Int,
    val route: List<Route>,
    val deep_link: String
)

class CodeName(
    val code: String,
    val name: String
)
class Route(
    val mapIdto: String
)