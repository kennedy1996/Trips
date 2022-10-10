package com.example.trips

import retrofit2.http.GET

interface BestTripsApi {

    @GET("flights?v=3&sort=popularity&asc=0&locale=aen&daysInDestinationFrom=&daysInDestinationTo=&affilid=&children=0&infants=0&flyFrom=49.2-16.61-250km&to=anywhere&featureName=aggregateResults&dateFrom=09/10/2022&dateTo=15/10/2022&typeFlight=oneway&returnFrom=&returnTo=&one_per_date=0&oneforcity=1&wait_for_refresh=0&adults=1&limit=45&partner=skypicker-android")
    suspend fun search(): BestTripReturn

}
