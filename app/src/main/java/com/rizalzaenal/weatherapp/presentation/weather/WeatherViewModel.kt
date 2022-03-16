package com.rizalzaenal.weatherapp.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalzaenal.weatherapp.domain.model.Location
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
    private val _weatherForecastState = MutableStateFlow<State<WeatherForecast>>(State.Empty)
    val weatherForecastState: StateFlow<State<WeatherForecast>> = _weatherForecastState

    fun getWeatherForecast(location: Location?) {
        _weatherForecastState.value = State.Loading
        if (location == null) {
            _weatherForecastState.value = State.Error("Please choose location first")
            //to emit error value only once, error state replaced after emitted
            _weatherForecastState.value = State.Empty
            return
        }
        viewModelScope.launch {
            try {
                val weatherForecast = getWeatherForecastUseCase(location.lat, location.lon)
                _weatherForecastState.value = State.Success(weatherForecast)
            } catch (e: Exception) {
                _weatherForecastState.value = getErrorMessage(e)
                //to emit error value only once, error state replaced after emitted
                _weatherForecastState.value = State.Empty
            }

        }
    }

}