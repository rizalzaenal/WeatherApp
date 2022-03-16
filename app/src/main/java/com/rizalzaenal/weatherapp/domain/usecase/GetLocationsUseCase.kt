package com.rizalzaenal.weatherapp.domain.usecase

import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.domain.repository.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(query: String): List<Location> {
        return locationRepository.getLocations(query)
    }
}