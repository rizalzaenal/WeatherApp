package com.rizalzaenal.weatherapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun Int?.replaceNull() : Int {
    return this ?: 0
}

fun String?.replaceNull() : String {
    return this ?: ""
}

fun Double?.replaceNull() : Double {
    return this ?: 0.0
}

fun Long?.replaceNull() : Long {
    return this ?: 0
}

fun ImageView.loadWeatherIcon(icon: String) {
    Glide.with(this)
        .load("https://openweathermap.org/img/wn/$icon@4x.png")
        .into(this)
}

fun Double.roundAndAddTempMetric(metric: String = "°C"): String {
    val rounded = this.roundToInt()
    return "$rounded$metric"
}

fun getHourFromEpoch(timeStamp: Long): String {
    //timeStamp times 1000 to changes from second epoch to millisecond
    val date = Date(timeStamp * 1000)
    val sdf = SimpleDateFormat("hh:mm aa", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.format(date)
}

fun getDayFromEpoch(timeStamp: Long): String {
    //timeStamp times 1000 to changes from second epoch to millisecond
    val date = Date(timeStamp * 1000)
    val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.format(date)
}