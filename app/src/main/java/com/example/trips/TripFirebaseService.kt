package com.example.trips

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class TripFirebaseService {
    private val db = FirebaseFirestore.getInstance(Firebase.app("bd-firebase"))
    private val colecao = db.collection("trip")

    private fun converteParaCC(documento: DocumentSnapshot): Trip? =
        documento.toObject(DocumentConverter::class.java)?.forTrip(documento.id)

    fun sendTripToFirebase(trip: Trip){
        colecao.document().set(trip)
    }

    fun searchFirebaseData(): List<Trip>{
        val listOldTrips = mutableListOf<Trip>()

        colecao
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let { snapshot ->
                    val produtos: List<Trip> = snapshot.documents
                        .mapNotNull { documento ->
                            converteParaCC(documento)
                        }
                    produtos.forEach { produtos ->
                        val material = Trip(
                            produtos.id,
                            produtos.date,
                            produtos.id_trip
                        )
                        listOldTrips.add(material)
                    }
                }
            }
        return listOldTrips
    }
}