package com.example.trips.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trips.R
import com.example.trips.inicializator.inicializatorFirebase
import com.example.trips.viewModel.BestTripViewModel

class TripActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(BestTripViewModel::class.java)
    }
    private var adapter: RecyclerView.Adapter<TripAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip)
        inicializatorFirebase(this)
        observersParaLiveData()
        viewModel.searchOldTrips()

        viewModel.search()

        val button = findViewById<Button>(R.id.button_test)
        button.setOnClickListener {
            viewModel.search()
        }

        val recyclerView: RecyclerView = findViewById(R.id.activity_trip_recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = TripAdapter(
            this,
            viewModel
        )
        recyclerView.adapter = adapter
    }

    private fun observersParaLiveData() {
        viewModel.getSearch()?.observe(this, Observer {
            adapter?.notifyDataSetChanged()
        })
    }
}