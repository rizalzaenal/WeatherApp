package com.rizalzaenal.weatherapp.presentation.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.domain.model.WeatherForecast
import com.rizalzaenal.weatherapp.domain.usecase.*
import com.rizalzaenal.weatherapp.presentation.State
import com.rizalzaenal.weatherapp.utils.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val addFavoriteLocationUseCase: AddFavoriteLocationUseCase,
    private val deleteFavoriteLocationUseCase: DeleteFavoriteLocationUseCase,
    private val getLatestLocationUseCase: GetLatestLocationUseCase,
    private val checkLocationAlreadyFavoriteUseCase: CheckLocationAlreadyFavoriteUseCase
) :
    ViewModel() {
    private val _weatherForecastState = MutableStateFlow<State<WeatherForecast>>(State.Empty)
    val weatherForecastState: StateFlow<State<WeatherForecast>> = _weatherForecastState

    private val _locationState = MutableStateFlow<Location?>(Location.EMPTY)
    val locationState: StateFlow<Location?> = _locationState

    private val _favoriteState = MutableStateFlow<Boolean>(false)
    val favoriteState: StateFlow<Boolean> = _favoriteState

    fun getWeatherForecast() {
        _weatherForecastState.value = State.Loading
        viewModelScope.launch {
            try {
                val location = getLatestLocationUseCase()
                if (location == null) {
                    _locationState.value = null
                    _weatherForecastState.value = State.Error("Please choose location first")
                    //to emit error value only once, error state replaced after emitted
                    _weatherForecastState.value = State.Empty
                    return@launch
                }
                _locationState.value = location
                _favoriteState.value =
                    checkLocationAlreadyFavoriteUseCase(location.lat, location.lon)

                val weatherForecast = getWeatherForecastUseCase(location.lat, location.lon)
                _weatherForecastState.value = State.Success(weatherForecast)
                //addFavoriteLocationUseCase(location)
            } catch (e: Exception) {
                _weatherForecastState.value = getErrorMessage(e)
                _weatherForecastState.value = State.Empty
            }
        }
    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            try {
                val location = getLatestLocationUseCase() ?: return@launch
                if (_favoriteState.value) {
                    deleteFavoriteLocationUseCase(location.lat, location.lon)
                } else {
                    addFavoriteLocationUseCase(location)
                }
                _favoriteState.value = checkLocationAlreadyFavoriteUseCase(location.lat, location.lon)
            } catch (e: Exception) {
                _weatherForecastState.value = getErrorMessage(e)
                _weatherForecastState.value = State.Empty
            }
        }
    }

}