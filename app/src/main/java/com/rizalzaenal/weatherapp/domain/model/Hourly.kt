package com.rizalzaenal.weatherapp.domain.model

data class Hourly(
    val weather: Weather,
    val unixDateTime: Int,
    val humidity: Int,
    val lastHourRainVolume: Double,
    val temp: Double
) {
    companion object {
        val EMPTY = Hourly(Weather.EMPTY, 0, 0, 0.0, 0.0)
    }
}

