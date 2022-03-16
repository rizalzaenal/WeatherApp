package com.rizalzaenal.weatherapp.domain.usecase

import com.rizalzaenal.weatherapp.domain.repository.LocationRepository
import javax.inject.Inject

class CheckLocationAlreadyFavoriteUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): Boolean {
        return locationRepository.isAlreadyFavorite(lat, lon) > 0
    }
}