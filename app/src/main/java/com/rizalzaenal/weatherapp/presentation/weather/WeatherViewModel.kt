package com.rizalzaenal.weatherapp.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalzaenal.weatherapp.domain.model.WeatherForecast
import com.rizalzaenal.weatherapp.domain.usecase.GetWeatherForecastUseCase
import com.rizalzaenal.weatherapp.presentation.State
import com.rizalzaenal.weatherapp.utils.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val getWeatherForecastUseCase: GetWeatherForecastUseCase) :
    ViewModel() {
    private val _weatherForecastState = MutableStateFlow<State<WeatherForecast>>(State.Init)
    val weatherForecastState: StateFlow<State<WeatherForecast>> = _weatherForecastState

    fun getWeatherForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _weatherForecastState.value = State.Loading
            try {
                val weatherForecast = getWeatherForecastUseCase(latitude, longitude)
                _weatherForecastState.value = State.Success(weatherForecast)
            } catch (e: Exception) {
                _weatherForecastState.value = getErrorMessage(e)
            }

        }
    }

}