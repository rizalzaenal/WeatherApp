package com.rizalzaenal.weatherapp.data.local

import androidx.room.*
import com.rizalzaenal.weatherapp.data.model.LocationEntity

@Dao
interface FavoriteLocationsDao {

    @Query("SELECT * FROM favorites_locations_table")
    suspend fun getFavoriteLocations(): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteLocation(locationEntity: LocationEntity)

    @Query("DELETE FROM favorites_locations_table WHERE lat = :lat AND lon = :lon")
    suspend fun delete(lat: Double, lon: Double)

    @Query("SELECT Count() FROM favorites_locations_table WHERE  lat = :lat AND lon = :lon")
    //If returned Int > 0 location already favorited
    suspend fun isAlreadyFavorite(lat: Double, lon: Double): Int
}