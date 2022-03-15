package com.rizalzaenal.weatherapp.utils

fun Int?.replaceNull() : Int {
    return this ?: 0
}

fun String?.replaceNull() : String {
    return this ?: ""
}

fun Double?.replaceNull() : Double {
    return this ?: 0.0
}