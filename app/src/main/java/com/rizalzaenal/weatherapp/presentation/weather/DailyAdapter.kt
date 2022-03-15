package com.rizalzaenal.weatherapp.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizalzaenal.weatherapp.R
import com.rizalzaenal.weatherapp.databinding.DailyItemBinding
import com.rizalzaenal.weatherapp.domain.model.Daily
import com.rizalzaenal.weatherapp.utils.getDayFromEpoch
import com.rizalzaenal.weatherapp.utils.loadWeatherIcon
import com.rizalzaenal.weatherapp.utils.roundTemp

class DailyAdapter: RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {
    private val data: ArrayList<Daily> = arrayListOf()
    var timeZoneOffset = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val binding = DailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(data[position], timeZoneOffset)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setItems(list: List<Daily>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class DailyViewHolder(private val binding: DailyItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Daily, timezoneOffset: Int) {
            binding.tvTemp.text = data.temp.roundTemp()
            binding.tvTempDesc.text = data.weather.description
            binding.ivWeatherIcon.loadWeatherIcon(data.weather.icon)
            binding.tvDate.text = getDayFromEpoch(data.unixDateTime + timezoneOffset)
            binding.tvHumidity.text = binding.root.context.getString(R.string.humidity, data.humidity)
            binding.tvWind.text = binding.root.context.getString(R.string.wind_speed, data.windSpeed)

        }

    }
}