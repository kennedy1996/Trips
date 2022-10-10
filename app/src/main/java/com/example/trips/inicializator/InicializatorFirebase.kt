package com.example.trips.inicializator

import android.content.Context
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

fun inicializatorFirebase(context: Context) {
    val options = FirebaseOptions.Builder()
        .setProjectId("trips-ad0c5")
        .setApplicationId("1:467017921362:android:1708af7bc68aaff6a4808b")
        .build()
    try {
        Firebase.initialize(context, options, "bd-firebase")
    } catch (e: Exception) {
    }
}