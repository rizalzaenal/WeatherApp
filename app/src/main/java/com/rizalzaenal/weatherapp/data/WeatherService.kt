package com.rizalzaenal.weatherapp.data

import com.rizalzaenal.weatherapp.data.model.LocationsResponse
import com.rizalzaenal.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/onecall")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): WeatherResponse

    @GET("geo/1.0/direct")
    suspend fun getLocations(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): List<LocationsResponse>

}