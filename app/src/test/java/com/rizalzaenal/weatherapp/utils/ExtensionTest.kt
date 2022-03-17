package com.rizalzaenal.weatherapp.utils

import org.junit.Assert
import org.junit.Test

class ExtensionTest {

    @Test
    fun `roundTemp test round down`() {
        val double = 22.40
        val result = double.roundAndAddTempMetric()
        val resultFahrenheit = double.roundAndAddTempMetric("°F")

        Assert.assertEquals("22°C", result)
        Assert.assertEquals("22°F", resultFahrenheit)
    }

    @Test
    fun `roundTemp test round up`() {
        val double = 22.70
        val result = double.roundAndAddTempMetric()
        val resultFahrenheit = double.roundAndAddTempMetric("°F")

        Assert.assertEquals("23°C", result)
        Assert.assertEquals("23°F", resultFahrenheit)
    }

    @Test
    fun `getHourFromEpoch test`() {
        val epochHour: Long = 1641039600
        val result = getHourFromEpoch(epochHour)
        val expected = "12:20 PM"

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `getDayFromEpoch test`() {
        val epochHour: Long = 1641039600
        val result = getDayFromEpoch(epochHour)
        val expected = "Saturday"

        Assert.assertEquals(expected, result)
    }
}