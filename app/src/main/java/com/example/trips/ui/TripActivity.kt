package com.example.trips.ui

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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
        title= "See Unique Trips for you Today!"
        inicializatorFirebase(this)
        observersParaLiveData()
        settingsFirstSearch()
        settingsButtonUpdate()
        settingsRecyclerView()
        checkDataAfterTime()
    }

    private fun checkDataAfterTime() {
        val h = Handler()
        h.postDelayed({
            if (viewModel.getSearch().value?.size == 0) {
                viewModel.search()
            }
        }, 6000)
    }

    private fun settingsFirstSearch() {
        viewModel.searchOldTrips()
        viewModel.search()
    }

    private fun settingsButtonUpdate() {
        val button = findViewById<Button>(R.id.activity_trip_update)
        button.setOnClickListener {
            viewModel.search()
        }
    }

    private fun settingsRecyclerView() {
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