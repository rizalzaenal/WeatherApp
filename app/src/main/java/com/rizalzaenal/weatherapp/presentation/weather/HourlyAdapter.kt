package com.rizalzaenal.weatherapp.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizalzaenal.weatherapp.databinding.HourlyItemBinding
import com.rizalzaenal.weatherapp.domain.model.Hourly
import com.rizalzaenal.weatherapp.utils.getHourFromEpoch
import com.rizalzaenal.weatherapp.utils.loadWeatherIcon
import com.rizalzaenal.weatherapp.utils.roundAndAddTempMetric

class HourlyAdapter: RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {
    private val data: ArrayList<Hourly> = arrayListOf()
    var timeZoneOffset = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val binding = HourlyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(data[position], timeZoneOffset)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setItems(list: List<Hourly>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class HourlyViewHolder(private val binding: HourlyItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Hourly, timezoneOffset: Int) {
            binding.tvTemp.text = data.temp.roundAndAddTempMetric()
            binding.ivWeatherIcon.loadWeatherIcon(data.weather.icon)
            binding.tvTime.text = getHourFromEpoch(data.unixDateTime + timezoneOffset)

        }

    }
}