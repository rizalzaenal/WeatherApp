package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.DataHourly
import com.rizalzaenal.weatherapp.data.model.DataRain
import org.junit.Assert.*
import org.junit.Test

class HourlyMapperTest {
    private val dataHourly = DataHourly(
        1, 2.0, 3, 4.0, 5, 6.0, 7, DataRain(8.0), 9.0, 10.0, 11, listOf(),
        12, 13.0, 14.0
    )

    @Test
    fun `test daily mapper to domain model`() {
        val mapper = HourlyMapper()
        val domainHourly = mapper.toDomainModel(dataHourly)

        assertEquals(domainHourly.unixDateTime, dataHourly.dt)
        assertEquals(domainHourly.lastHourRainVolume, dataHourly.rain?.h)
        assertEquals(domainHourly.humidity, dataHourly.humidity)
        assertEquals(domainHourly.temp, dataHourly.temp)
    }
}