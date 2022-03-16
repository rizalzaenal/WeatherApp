package com.rizalzaenal.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
): Parcelable {
    companion object {
        val EMPTY = Location("", 0.0, 0.0, "", "")
    }
}