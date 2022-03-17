package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataWeather
import com.rizalzaenal.weatherapp.domain.model.Weather
import org.junit.Assert.*
import org.junit.Test

class WeatherMapperTest {
    private val dataWeather = DataWeather(
        "a", "b", 1, "c"
    )

    private val weather = Weather(
        "a", "b", 1, "c"
    )

    @Test
    fun `test weather mapper to domain model`() {
        val mapper = WeatherMapper()
        val domainWeather = mapper.toDomainModel(dataWeather)

        assertEquals(domainWeather.description, dataWeather.description)
        assertEquals(domainWeather.icon, dataWeather.icon)
        assertEquals(domainWeather.id, dataWeather.id)
        assertEquals(domainWeather.main, dataWeather.main)
    }

    @Test
    fun `test weather mapper from domain model`() {
        val mapper = WeatherMapper()
        val dataWeather = mapper.fromDomainModel(weather)

        assertEquals(dataWeather.description, weather.description)
        assertEquals(dataWeather.icon, weather.icon)
        assertEquals(dataWeather.id, weather.id)
        assertEquals(dataWeather.main, weather.main)
    }
}