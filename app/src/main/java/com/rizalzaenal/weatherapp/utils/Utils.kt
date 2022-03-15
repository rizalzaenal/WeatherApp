package com.rizalzaenal.weatherapp.utils

import com.rizalzaenal.weatherapp.presentation.State
import java.io.IOException
import java.net.SocketTimeoutException

fun getErrorMessage(e: Exception): State.Error {
    return when (e) {
        is IOException -> {
            State.Error("No Internet, please check your connections...")
        }
        else -> {
            State.Error(e.localizedMessage.replaceNull())
        }
    }
}