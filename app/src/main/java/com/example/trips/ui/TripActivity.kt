package com.example.trips.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.trips.R
import com.example.trips.inicializator.inicializatorFirebase
import com.example.trips.viewModel.BestTripViewModel

class TripActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(BestTripViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializatorFirebase(this)
        viewModel.searchOldTrips()

        val button = findViewById<Button>(R.id.button_test)
        button.setOnClickListener {
            viewModel.search()
        }
    }
}