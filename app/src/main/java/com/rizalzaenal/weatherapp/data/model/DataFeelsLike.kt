package com.rizalzaenal.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class DataFeelsLike(
    @SerializedName("day")
    val day: Double?,
    @SerializedName("eve")
    val eve: Double?,
    @SerializedName("morn")
    val morn: Double?,
    @SerializedName("night")
    val night: Double?
)