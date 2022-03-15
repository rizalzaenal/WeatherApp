package com.rizalzaenal.weatherapp.presentation.weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.rizalzaenal.weatherapp.R
import com.rizalzaenal.weatherapp.presentation.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment: Fragment(R.layout.fragment_weather) {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.weatherForecastState
                    .collect {
                        when (it) {
                            is State.Loading -> {}
                            is State.Success -> {
                                Toast.makeText(requireContext(), it.data.timezone, Toast.LENGTH_SHORT).show()
                            }
                            is State.Error -> {
                                Toast.makeText(requireContext(), it.exception.message, Toast.LENGTH_SHORT).show()
                            }
                            else -> {}
                        }
                    }
            }
        }

        viewModel.getWeatherForecast(-6.9344694, 107.6049539)
    }
}