package com.rizalzaenal.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizalzaenal.weatherapp.data.model.LatestLocationEntity
import com.rizalzaenal.weatherapp.data.model.LocationEntity

@Database(entities = [LocationEntity::class, LatestLocationEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteLocationsDao(): FavoriteLocationsDao
    abstract fun latestLocationDao(): LatestLocationDao

    companion object {
        private const val DB_NAME = "location_database"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}