package com.rizalzaenal.weatherapp.domain.usecase

import com.rizalzaenal.weatherapp.domain.model.WeatherForecast
import com.rizalzaenal.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): WeatherForecast {
        return weatherRepository.getWeatherForecast(latitude, longitude)
    }
}