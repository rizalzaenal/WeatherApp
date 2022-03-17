package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataCurrent
import com.rizalzaenal.weatherapp.data.model.DataRain
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class CurrentMapperTest {
    private val dataCurrent = DataCurrent(
        1, 2.0, 3, 4.0, 5, 6, DataRain(7.0),
        8, 9, 10.0, 11.0, 12, listOf(),
        13.0, 14.0, 15.0
    )

    @Test
    fun `test current mapper to domain model`() {
        val mapper = CurrentMapper()
        val domainCurrent = mapper.toDomainModel(dataCurrent)

        assertEquals(domainCurrent.windSpeed, dataCurrent.windSpeed)
        assertEquals(domainCurrent.humidity, dataCurrent.humidity)
        assertEquals(domainCurrent.lastHourRainVolume, dataCurrent.rain?.h)
        assertEquals(domainCurrent.sunrise, dataCurrent.sunrise)
        assertEquals(domainCurrent.sunset, dataCurrent.sunset)
        assertEquals(domainCurrent.temp, dataCurrent.temp)
        assertEquals(domainCurrent.unixDateTime, dataCurrent.dt)
    }
}