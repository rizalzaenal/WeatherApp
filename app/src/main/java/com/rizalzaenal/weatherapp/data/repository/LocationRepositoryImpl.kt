package com.rizalzaenal.weatherapp.data.repository

import com.rizalzaenal.weatherapp.data.WeatherService
import com.rizalzaenal.weatherapp.data.mapper.LocationMapper
import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.domain.repository.LocationRepository

class LocationRepositoryImpl(private val service: WeatherService): LocationRepository {

    override suspend fun getLocations(query: String, limit: Int, appid: String): List<Location> {
        val locationMapper = LocationMapper()
        val response = service.getLocations(query, limit, appid)
        return response.map { locationMapper.toDomainModel(it) }
    }

}