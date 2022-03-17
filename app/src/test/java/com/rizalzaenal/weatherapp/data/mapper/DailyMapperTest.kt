package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataDaily
import com.rizalzaenal.weatherapp.data.model.DataTemp
import org.junit.Assert.*
import org.junit.Test

class DailyMapperTest {
    private val dataDaily = DataDaily(
        1, 2.0, 3, null, 4, 5.0, 6, 7,
        8.0, 9, 10.0, 11, 12, DataTemp(11.0, 12.0, 13.0,
            14.0, 15.0, 16.0), 17.0, listOf(), 18, 19.0, 20.0
    )

    @Test
    fun `test daily mapper to domain model`() {
        val mapper = DailyMapper()
        val domainDaily = mapper.toDomainModel(dataDaily)

        assertEquals(domainDaily.unixDateTime, dataDaily.dt)
        assertEquals(domainDaily.temp, dataDaily.temp?.day)
        assertEquals(domainDaily.sunset, dataDaily.sunset)
        assertEquals(domainDaily.sunrise, dataDaily.sunrise)
        assertEquals(domainDaily.lastHourRainVolume, dataDaily.rain)
        assertEquals(domainDaily.humidity, dataDaily.humidity)
        assertEquals(domainDaily.windSpeed, dataDaily.windSpeed)
    }
}