package com.rizalzaenal.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "latest_location_table")
data class LatestLocationEntity(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)