package com.example.rviciana.safeflight.view.map

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rviciana.safeflight.R
import com.example.rviciana.safeflight.domain.bo.Schedule
import com.example.rviciana.safeflight.extensions.inflate
import kotlinx.android.synthetic.main.flight_item.view.*

class FlightsListAdapter : RecyclerView.Adapter<FlightsViewHolder>() {

    private var scheduleList: List<Schedule> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder
            = FlightsViewHolder(parent.inflate(R.layout.flight_item))

    override fun getItemCount(): Int = scheduleList.size

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int)
            = holder.bind(scheduleList[position])

    fun addFlights(scheduleList: List<Schedule>) {
        this.scheduleList = scheduleList
        notifyDataSetChanged()
    }
}

class FlightsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(schedule: Schedule) = with(itemView) {
        val context = itemView.context
        flightTime.text = String.format(
                context.getString(R.string.flight_time_label),
                schedule.totalJourney.duration
        )
    }
}