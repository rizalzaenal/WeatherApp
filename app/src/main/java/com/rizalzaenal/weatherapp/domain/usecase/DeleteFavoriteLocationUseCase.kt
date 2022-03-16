package com.rizalzaenal.weatherapp.domain.usecase

import com.rizalzaenal.weatherapp.domain.repository.LocationRepository
import javax.inject.Inject

class DeleteFavoriteLocationUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(lat: Double, lon: Double) {
        locationRepository.deleteFavoriteLocation(lat, lon)
    }
}