package com.rizalzaenal.weatherapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizalzaenal.weatherapp.databinding.LocationItemBinding
import com.rizalzaenal.weatherapp.domain.model.Location

class SearchAdapter(private val onItemClick: (Location) -> Unit): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private val data: ArrayList<Location> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(data[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setItems(list: List<Location>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class SearchViewHolder(private val binding: LocationItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Location, onItemClick: (Location) -> Unit) {
            val location = if (data.state.isNotEmpty()) {
                "${data.name}, ${data.state}, ${data.country}"
            }else {
                "${data.name}, ${data.country}"
            }
            binding.tvLocationName.text = location
            binding.root.setOnClickListener { onItemClick(data) }
        }
    }
}