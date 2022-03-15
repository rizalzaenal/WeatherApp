package com.rizalzaenal.weatherapp.presentation.weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.google.android.material.snackbar.Snackbar
import com.rizalzaenal.weatherapp.R
import com.rizalzaenal.weatherapp.databinding.FragmentWeatherBinding
import com.rizalzaenal.weatherapp.presentation.State
import com.rizalzaenal.weatherapp.utils.getHourFromEpoch
import com.rizalzaenal.weatherapp.utils.loadWeatherIcon
import com.rizalzaenal.weatherapp.utils.roundTemp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private var _binding: FragmentWeatherBinding? = null
    val binding: FragmentWeatherBinding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeatherBinding.bind(view)
        addListener()
        initRecyclerView()
        collectState()

        viewModel.getWeatherForecast(-6.9344694, 107.6049539)
    }

    private fun addListener() {
        binding.ivOverflow.setOnClickListener {
            showMenu()
        }

        binding.ivSearch.setOnClickListener {
            Toast.makeText(requireContext(), "search", Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getWeatherForecast(-6.9344694, 107.6049539)
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.weatherForecastState
                    .collect {
                        when (it) {
                            is State.Loading -> {
                                binding.swipeRefresh.isRefreshing = true
                            }
                            is State.Success -> {
                                binding.swipeRefresh.isRefreshing = false
                                binding.tvLocationName.text = it.data.timezone
                                (binding.rvDaily.adapter as DailyAdapter).apply {
                                    timeZoneOffset = it.data.timezoneOffset
                                    setItems(it.data.daily)
                                }
                                (binding.rvHourly.adapter as HourlyAdapter).apply {
                                    timeZoneOffset = it.data.timezoneOffset
                                    setItems(it.data.hourly)
                                }
                                binding.cardCurrent.apply {
                                    val timeStampWithOffset = it.data.current.unixDateTime + it.data.timezoneOffset
                                    ivWeatherIcon.loadWeatherIcon(it.data.current.weather.icon)
                                    tvTemp.text = it.data.current.temp.roundTemp()
                                    tvTempDesc.text = it.data.current.weather.description
                                    tvDate.text = getHourFromEpoch(timeStampWithOffset)
                                    tvHumidity.text = getString(R.string.humidity, it.data.current.humidity)
                                    tvRainVolume.text = getString(R.string.rain_volume, it.data.current.lastHourRainVolume)

                                }
                            }
                            is State.Error -> {
                                binding.swipeRefresh.isRefreshing = false
                                Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG)
                                    .setAction("Try again") {
                                        viewModel.getWeatherForecast(-6.9344694, 107.6049539)
                                    }
                                    .show()
                            }
                            else -> {}
                        }
                    }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvDaily.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DailyAdapter()
            LinearSnapHelper().attachToRecyclerView(binding.rvDaily)
        }

        binding.rvHourly.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = HourlyAdapter()
        }
    }

    private fun showMenu() {
        val popup = PopupMenu(requireContext(), binding.ivOverflow)
        popup.inflate(R.menu.weather_menu)
        popup.show()
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorite -> {
                    Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                else -> { return@setOnMenuItemClickListener false }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}