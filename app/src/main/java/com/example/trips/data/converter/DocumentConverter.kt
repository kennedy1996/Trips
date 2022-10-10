package com.example.trips.data.converter

import android.util.Log
import com.example.trips.data.entites.Trip

class DocumentConverter(
    val id : String = "",
    val date : String = "",
    val id_trip: String = ""
) {
    fun forTrip(id: String): Trip {

        Log.i("CCDocs", "$id |  $id_trip | $date")

        return Trip(
            id = id,
            date = date,
            id_trip = id_trip
        )
    }

}
