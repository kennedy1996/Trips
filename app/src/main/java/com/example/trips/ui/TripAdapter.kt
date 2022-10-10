package com.example.trips.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.example.trips.R
import com.example.trips.convertLongToString
import com.example.trips.viewModel.BestTripViewModel
import com.nostra13.universalimageloader.core.ImageLoader

class TripAdapter(
    private val context: Context,
    private val viewModel: BestTripViewModel,
) : RecyclerView.Adapter<TripAdapter.ViewHolder>() {

    var list = viewModel.getSearch()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.activity_trip_item_image)
        var trip_from: TextView = itemView.findViewById(R.id.activity_trip_item_trip_from)
        var duration: TextView = itemView.findViewById(R.id.activity_trip_item_duration)
        var price: Button = itemView.findViewById(R.id.activity_trip_item_price)
        var trip_to: TextView = itemView.findViewById(R.id.activity_trip_item_trip_to)
        var date: TextView = itemView.findViewById(R.id.activity_trip_item_date)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.activity_trip_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val cityFrom = list?.value?.get(position)?.cityFrom
            val contryFrom = list?.value?.get(position)?.countryFrom!!.name
            val cityTo = list?.value?.get(position)?.cityTo
            val contryTo = list?.value?.get(position)?.countryTo!!.name

            holder.duration.text = list?.value?.get(position)?.fly_duration
            holder.trip_from.text = "from\n$cityFrom\n$contryFrom"
            holder.trip_to.text = "to\n$cityTo\n$contryTo"
            holder.price.text = list?.value?.get(position)?.price.toString()
            holder.date.text = convertLongToString(list?.value?.get(position)?.dTime!!  )


            loadingImage(position, holder)
            onItemClickAccessKiwiSite(holder, position)

        } catch (e: Exception) {
            Log.e("TripAdapter", e.message.toString())
        }
    }

    private fun onItemClickAccessKiwiSite(
        holder: ViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            val url = list?.value?.get(position)?.deep_link
            val intent2 = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            it.context.startActivity(intent2)
        }
    }

    override fun getItemCount(): Int {
        return list?.value?.count() ?: 0
    }

    private fun loadingImage(
        position: Int,
        holder: ViewHolder
    ) {
        val link = list?.value?.get(position)?.route?.get(0)?.mapIdto
        val url = "https://images.kiwi.com/photos/600x330/${link}.jpg"
        val imageLoader = holder.image.context.imageLoader
        val request = ImageRequest.Builder(context)
            .data(url)
            .target(holder.image)
            .build()
        imageLoader.enqueue(request)
    }
}
