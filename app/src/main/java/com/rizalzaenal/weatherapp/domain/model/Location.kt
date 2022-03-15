package com.rizalzaenal.weatherapp.domain.model


data class Location(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
) {
    val EMPTY = Location("", 0.0, 0.0, "", "")
}