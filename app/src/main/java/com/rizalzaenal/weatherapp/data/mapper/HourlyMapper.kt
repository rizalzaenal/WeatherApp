package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataHourly
import com.rizalzaenal.weatherapp.data.model.DataWeather
import com.rizalzaenal.weatherapp.domain.model.Hourly
import com.rizalzaenal.weatherapp.utils.replaceNull

class HourlyMapper: Mapper<DataHourly, Hourly> {

    override fun toDomainModel(data: DataHourly): Hourly {
        val weatherMapper = WeatherMapper()
        return Hourly(
            weatherMapper.toDomainModel(data.weather?.firstOrNull() ?: DataWeather()),
            data.dt.replaceNull(),
            data.humidity.replaceNull(),
            data.rain?.h.replaceNull(),
            data.temp.replaceNull()
        )
    }

    override fun fromDomainModel(data: Hourly): DataHourly {
        TODO("Not yet implemented")
    }
}