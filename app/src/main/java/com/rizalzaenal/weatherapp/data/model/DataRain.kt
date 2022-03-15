package com.rizalzaenal.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class DataRain(
    @SerializedName("1h")
    val h: Double?
)