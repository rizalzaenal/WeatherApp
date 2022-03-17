package com.rizalzaenal.weatherapp.data.repository

import com.rizalzaenal.weatherapp.data.WeatherService
import com.rizalzaenal.weatherapp.data.model.DataCurrent
import com.rizalzaenal.weatherapp.data.model.DataRain
import com.rizalzaenal.weatherapp.data.model.WeatherResponse
import com.rizalzaenal.weatherapp.domain.model.Daily
import com.rizalzaenal.weatherapp.domain.model.WeatherForecast
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WeatherRepositoryImplTest {
    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl
    private lateinit var weatherService: WeatherService

    private val dataCurrent = DataCurrent(
        1, 2.0, 3, 4.0, 5, 6, DataRain(7.0),
        8, 9, 10.0, 11.0, 12, listOf(),
        13.0, 14.0, 15.0
    )
    private val weatherResponse = WeatherResponse(
        null,
        listOf(),
        listOf(),
        1.0,
        2.0,
        "a",
        3
    )

    private val weatherForecast = WeatherForecast(
        Daily.EMPTY,
        listOf(),
        listOf(),
        1.0,
        2.0,
        "a",
        3
    )

    @Before
    fun setUp() {
        weatherService = mock()
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherService)

        runBlocking {
            whenever(weatherService.getWeatherData(any(), any(), any(), any(), any()))
                .thenReturn(weatherResponse)
        }
    }

    @Test
    fun `test getWeather return WeatherForecast`() {
        runBlocking {
            val result = weatherRepositoryImpl.getWeatherForecast(1.0, 2.0)

            Assert.assertEquals(weatherForecast, result)
        }
    }
}