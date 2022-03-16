package com.rizalzaenal.weatherapp.data.repository

import com.rizalzaenal.weatherapp.data.WeatherService
import com.rizalzaenal.weatherapp.data.local.FavoriteLocationsDao
import com.rizalzaenal.weatherapp.data.local.LatestLocationDao
import com.rizalzaenal.weatherapp.data.mapper.LatestLocationEntityMapper
import com.rizalzaenal.weatherapp.data.mapper.LocationEntityMapper
import com.rizalzaenal.weatherapp.data.mapper.LocationMapper
import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val service: WeatherService,
    private val favoriteLocationsDao: FavoriteLocationsDao,
    private val latestLocationDao: LatestLocationDao
) : LocationRepository {

    override suspend fun getLocations(query: String, limit: Int, appid: String): List<Location> {
        val locationMapper = LocationMapper()
        val response = service.getLocations(query, limit, appid)
        return response.map { locationMapper.toDomainModel(it) }
    }

    override suspend fun getFavoriteLocations(): List<Location> {
        val locationEntityMapper = LocationEntityMapper()
        val favoriteLocations = favoriteLocationsDao.getFavoriteLocations()
        return favoriteLocations.map { locationEntityMapper.toDomainModel(it) }
    }

    override suspend fun addFavoriteLocation(location: Location) {
        val locationEntityMapper = LocationEntityMapper()
        val locationEntity = locationEntityMapper.fromDomainModel(location)
        favoriteLocationsDao.addFavoriteLocation(locationEntity)
    }

    override suspend fun getLatestLocation(): Location? {
        val mapper = LatestLocationEntityMapper()
        val location = latestLocationDao.getLatestLocation()
        return if (location == null) null else mapper.toDomainModel(location)
    }

    override suspend fun setLatestLocation(location: Location) {
        val mapper = LatestLocationEntityMapper()
        latestLocationDao.updateLatestLocation(mapper.fromDomainModel(location))
    }

    override suspend fun isAlreadyFavorite(lat: Double, lon: Double): Int {
        return favoriteLocationsDao.isAlreadyFavorite(lat, lon)
    }

    override suspend fun deleteFavoriteLocation(lat: Double, lon: Double) {
        return favoriteLocationsDao.delete(lat, lon)
    }
}