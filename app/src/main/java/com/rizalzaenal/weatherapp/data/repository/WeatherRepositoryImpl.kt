package com.rizalzaenal.weatherapp.data.repository

import com.rizalzaenal.weatherapp.data.WeatherService
import com.rizalzaenal.weatherapp.data.mapper.CurrentMapper
import com.rizalzaenal.weatherapp.data.mapper.DailyMapper
import com.rizalzaenal.weatherapp.data.mapper.HourlyMapper
import com.rizalzaenal.weatherapp.data.model.DataCurrent
import com.rizalzaenal.weatherapp.domain.model.WeatherForecast
import com.rizalzaenal.weatherapp.domain.repository.WeatherRepository
import com.rizalzaenal.weatherapp.utils.replaceNull

class WeatherRepositoryImpl(private val service: WeatherService) : WeatherRepository {

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        units: String,
        appid: String
    ): WeatherForecast {
        val response = service.getWeatherData(latitude, longitude, exclude, units, appid)
        val currentMapper = CurrentMapper()
        val hourlyMapper = HourlyMapper()
        val dailyMapper = DailyMapper()
        val current = currentMapper.toDomainModel(response.current ?: DataCurrent())
        val hourly = response.hourly?.map { hourlyMapper.toDomainModel(it) } ?: listOf()
        val daily = response.daily?.map { dailyMapper.toDomainModel(it) } ?: listOf()
        return WeatherForecast(
            current,
            hourly,
            daily,
            response.lat.replaceNull(),
            response.lon.replaceNull(),
            response.timezone.replaceNull(),
            response.timezoneOffset.replaceNull()
        )
    }
}