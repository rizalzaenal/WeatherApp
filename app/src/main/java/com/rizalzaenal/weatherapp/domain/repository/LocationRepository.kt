package com.rizalzaenal.weatherapp.domain.repository

import com.rizalzaenal.weatherapp.domain.model.Location

interface LocationRepository {
    suspend fun getLocations(query: String): List<Location>
}