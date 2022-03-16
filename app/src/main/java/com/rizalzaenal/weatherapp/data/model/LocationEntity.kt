package com.rizalzaenal.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favorites_locations_table")
data class LocationEntity(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)