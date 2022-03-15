package com.rizalzaenal.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class DataMinutely(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("precipitation")
    val precipitation: Double?
)