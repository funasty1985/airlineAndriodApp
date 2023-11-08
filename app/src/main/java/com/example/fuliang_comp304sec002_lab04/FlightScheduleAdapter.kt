package com.example.fuliang_comp304sec002_lab04

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.example.fuliang_comp304sec002_lab04.database.flightSchedule.Schedule
import com.example.fuliang_comp304sec002_lab04.databinding.FlightItemBinding
import java.text.SimpleDateFormat
import java.util.*

class FlightScheduleAdapter(
    private val onItemClicked: (Schedule) -> Unit
): ListAdapter<Schedule, FlightScheduleAdapter.FlightStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object: DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }

//    Override and implement onCreateViewHolder() and inflate the layout and set
//    the onClickListener() to call onItemClicked() for the item at the current position.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightStopViewHolder {
        val viewHolder = FlightStopViewHolder(
            FlightItemBinding.inflate(
                LayoutInflater.from( parent.context ),
                parent,
                false
            )
        )
        // when a flight_item is clicked
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FlightStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FlightStopViewHolder(
        // rmb to set data binding to true in gradle
        private var binding: FlightItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
            binding.airlineNameTextView.text = schedule.airline
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                "h:mm a").format(
                Date(schedule.arrivalTime.toLong() * 1000)
            )
            binding.terminalTextView.text = schedule.terminal
        }
    }
}