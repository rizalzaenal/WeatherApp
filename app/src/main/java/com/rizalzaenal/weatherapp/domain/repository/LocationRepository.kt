package com.rizalzaenal.weatherapp.domain.repository

import com.rizalzaenal.weatherapp.BuildConfig
import com.rizalzaenal.weatherapp.domain.model.Location

interface LocationRepository {
    suspend fun getLocations(query: String, limit: Int = 5, appid: String = BuildConfig.API_KEY): List<Location>
}