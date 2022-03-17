package com.rizalzaenal.weatherapp.utils

import org.junit.Assert
import org.junit.Test
import java.io.IOException

class UtilsTest {
    private val errorMessageNoInternet = "No Internet, please check your connections..."
    private val unknownError = "Unknown Error"

    @Test
    fun `test getErrorMessage when IOException  `() {
        val state = getErrorMessage(IOException())

        Assert.assertEquals(state.message, errorMessageNoInternet)
    }

    @Test
    fun `test getErrorMessage when Not IOException `() {
        val state = getErrorMessage(Exception(unknownError))

        Assert.assertEquals(state.message, unknownError)
    }
}