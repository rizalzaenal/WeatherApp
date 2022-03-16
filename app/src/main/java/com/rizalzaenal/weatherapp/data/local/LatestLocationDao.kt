package com.rizalzaenal.weatherapp.data.local

import androidx.room.*
import com.rizalzaenal.weatherapp.data.model.LatestLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LatestLocationDao {

    @Query("DELETE FROM latest_location_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(latestLocationEntity: LatestLocationEntity)

    @Transaction
    suspend fun updateLatestLocation(latestLocationEntity: LatestLocationEntity) {
        deleteAll()
        insert(latestLocationEntity)
    }

    @Query("SELECT * FROM latest_location_table LIMIT 1")
    suspend fun getLatestLocation(): LatestLocationEntity?
}