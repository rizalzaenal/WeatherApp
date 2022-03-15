package com.rizalzaenal.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val current: DataCurrent? = null,
    @SerializedName("daily")
    val daily: List<DataDaily>? = listOf(),
    @SerializedName("hourly")
    val hourly: List<DataHourly>? = listOf(),
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lon")
    val lon: Double? = null,
    @SerializedName("minutely")
    val minutely: List<DataMinutely>? = listOf(),
    @SerializedName("timezone")
    val timezone: String? = null,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int? = null
)