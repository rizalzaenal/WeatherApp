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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.google.android.material.snackbar.Snackbar
import com.rizalzaenal.weatherapp.R
import com.rizalzaenal.weatherapp.databinding.FragmentWeatherBinding
import com.rizalzaenal.weatherapp.presentation.State
import com.rizalzaenal.weatherapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeatherBinding.bind(view)
        addListener()
        initRecyclerView()
        collectState()
        viewModel.getWeatherForecast()
    }

    private fun addListener() {
        binding.ivOverflow.setOnClickListener { showMenu() }
        binding.ivSearch.setOnClickListener {
            findNavController().navigate(WeatherFragmentDirections.toSearchFragment())
        }
        binding.swipeRefresh.setOnRefreshListener { viewModel.getWeatherForecast() }
        binding.ivFavorite.setOnClickListener { viewModel.onFavoriteClick() }
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
                                (binding.rvDaily.adapter as DailyAdapter).apply {
                                    timeZoneOffset = it.data.timezoneOffset
                                    setItems(it.data.daily)
                                }
                                (binding.rvHourly.adapter as HourlyAdapter).apply {
                                    timeZoneOffset = it.data.timezoneOffset
                                    setItems(it.data.hourly)
                                }
                                binding.cardCurrent.apply {
                                    val timeStampWithOffset =
                                        it.data.current.unixDateTime + it.data.timezoneOffset
                                    ivWeatherIcon.loadWeatherIcon(it.data.current.weather.icon)
                                    tvTemp.text = it.data.current.temp.roundTemp()
                                    tvTempDesc.text = it.data.current.weather.description
                                    tvDate.text = getHourFromEpoch(timeStampWithOffset)
                                    tvHumidity.text =
                                        getString(R.string.humidity, it.data.current.humidity)
                                    tvRainVolume.text = getString(
                                        R.string.rain_volume,
                                        it.data.current.lastHourRainVolume
                                    )
                                }
                            }
                            is State.Error -> {
                                binding.swipeRefresh.isRefreshing = false
                                Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.try_again) {
                                        viewModel.getWeatherForecast()
                                    }.show()
                            }
                            else -> {}
                        }
                    }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.locationState
                    .filterNotNull()
                    .collect {
                        binding.tvLocationName.text = it.name
                    }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.favoriteState
                    .collect {
                        if (it) {
                            binding.ivFavorite.setImageResource(R.drawable.icon_heart_filled)
                        } else {
                            binding.ivFavorite.setImageResource(R.drawable.icon_heart_outline)
                        }
                    }
            }
        }

    }

    private fun initRecyclerView() {
        binding.rvDaily.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DailyAdapter()
            LinearSnapHelper().attachToRecyclerView(binding.rvDaily)
        }

        binding.rvHourly.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
                    findNavController().navigate(WeatherFragmentDirections.toFavoritesFragment())
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}