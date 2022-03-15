package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataWeather
import com.rizalzaenal.weatherapp.domain.model.Weather
import com.rizalzaenal.weatherapp.utils.replaceNull

class WeatherMapper : Mapper<DataWeather, Weather> {
    override fun toDomainModel(data: DataWeather): com.rizalzaenal.weatherapp.domain.model.Weather {
        return Weather(
            data.description.replaceNull(),
            data.icon.replaceNull(),
            data.id.replaceNull(),
            data.main.replaceNull()
        )
    }

    override fun fromDomainModel(data: com.rizalzaenal.weatherapp.domain.model.Weather): DataWeather {
        return DataWeather(
            data.description,
            data.icon,
            data.id,
            data.main
        )
    }
}