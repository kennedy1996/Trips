package com.example.trips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class TripActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(BestTripViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializatorFirebase()

        viewModel.searchOldTrips()

        val button = findViewById<Button>(R.id.button_test)
        button.setOnClickListener {
            viewModel.search()
//            viewModel.getsearchOldTrips()
        }
        val button2 = findViewById<Button>(R.id.button_test2)
        button2.setOnClickListener {

        }



    }

    private fun inicializatorFirebase() {
        val options = FirebaseOptions.Builder()
            .setProjectId("trips-ad0c5")
            .setApplicationId("1:467017921362:android:1708af7bc68aaff6a4808b")
            .build()
        try {
            Firebase.initialize(this, options, "bd-firebase")
        } catch (e: Exception) {
        }
    }
}