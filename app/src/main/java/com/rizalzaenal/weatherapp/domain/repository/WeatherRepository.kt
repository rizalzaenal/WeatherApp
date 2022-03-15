package com.rizalzaenal.weatherapp.domain.repository

import com.rizalzaenal.weatherapp.BuildConfig
import com.rizalzaenal.weatherapp.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String = "minutely",
        units: String = "metric",
        appid: String = BuildConfig.API_KEY
    ): WeatherForecast
}