package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataCurrent
import com.rizalzaenal.weatherapp.data.model.DataWeather
import com.rizalzaenal.weatherapp.domain.model.Daily
import com.rizalzaenal.weatherapp.domain.model.Weather
import com.rizalzaenal.weatherapp.utils.replaceNull

class CurrentMapper: Mapper<DataCurrent, Daily> {

    override fun toDomainModel(data: DataCurrent): Daily {
        val weatherMapper = WeatherMapper()
        return Daily(
            weatherMapper.toDomainModel(data.weather?.firstOrNull() ?: DataWeather()),
            data.dt.replaceNull(),
            data.humidity.replaceNull(),
            data.rain?.h.replaceNull(),
            data.sunrise.replaceNull(),
            data.sunset.replaceNull(),
            data.temp.replaceNull()
        )
    }

    override fun fromDomainModel(data: Daily): DataCurrent {
        TODO("Not yet implemented")
    }
}