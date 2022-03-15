package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataDaily
import com.rizalzaenal.weatherapp.data.model.DataWeather
import com.rizalzaenal.weatherapp.domain.model.Daily
import com.rizalzaenal.weatherapp.utils.replaceNull

class DailyMapper: Mapper<DataDaily, Daily> {
    override fun toDomainModel(data: DataDaily): Daily {
        val weatherMapper = WeatherMapper()
        return Daily(
            weatherMapper.toDomainModel(data.weather?.firstOrNull() ?: DataWeather()),
            data.dt.replaceNull(),
            data.humidity.replaceNull(),
            data.rain.replaceNull(),
            data.sunrise.replaceNull(),
            data.sunset.replaceNull(),
            data.temp?.day.replaceNull(),
            data.windSpeed.replaceNull()
        )
    }

    override fun fromDomainModel(data: Daily): DataDaily {
        TODO("Not yet implemented")
    }
}