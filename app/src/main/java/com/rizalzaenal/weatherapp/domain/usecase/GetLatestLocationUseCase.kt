package com.rizalzaenal.weatherapp.domain.usecase

import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestLocationUseCase @Inject constructor(private val locationRepository: LocationRepository) {

    suspend operator fun invoke(): Location? {
        return locationRepository.getLatestLocation()
    }

}