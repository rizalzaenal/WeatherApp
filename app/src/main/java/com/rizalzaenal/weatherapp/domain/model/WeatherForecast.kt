package com.rizalzaenal.weatherapp.domain.model

data class WeatherForecast(
    val current: Daily,
    val hourly: List<Hourly>,
    val daily: List<Daily>,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneOffset: Int
) {
    companion object {
        val EMPTY = WeatherForecast(Daily.EMPTY, listOf(), listOf(), 0.0,
            0.0, "", 0)
    }
}

