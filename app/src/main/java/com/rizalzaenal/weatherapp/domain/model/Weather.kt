package com.rizalzaenal.weatherapp.domain.model


data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) {
    companion object {
        val EMPTY = Weather("", "", 0, "")
    }
}

