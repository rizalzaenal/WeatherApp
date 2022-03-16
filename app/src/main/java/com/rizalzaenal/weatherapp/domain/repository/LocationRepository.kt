package com.rizalzaenal.weatherapp.domain.repository

import com.rizalzaenal.weatherapp.BuildConfig
import com.rizalzaenal.weatherapp.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocations(
        query: String,
        limit: Int = 5,
        appid: String = BuildConfig.API_KEY
    ): List<Location>

    suspend fun getFavoriteLocations(): List<Location>
    suspend fun addFavoriteLocation(location: Location)
    suspend fun getLatestLocation(): Location?
    suspend fun setLatestLocation(location: Location)
    suspend fun isAlreadyFavorite(lat: Double, lon: Double): Int
    suspend fun deleteFavoriteLocation(lat: Double, lon: Double)
}