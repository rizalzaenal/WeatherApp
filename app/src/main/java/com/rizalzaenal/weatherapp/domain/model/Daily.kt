package com.rizalzaenal.weatherapp.domain.model

data class Daily(
    val weather: Weather,
    val unixDateTime: Int,
    val humidity: Int,
    val lastHourRainVolume: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double
) {
    companion object {
        val EMPTY = Daily(Weather.EMPTY, 0, 0, 0.0,
            0, 0, 0.0)
    }
}
