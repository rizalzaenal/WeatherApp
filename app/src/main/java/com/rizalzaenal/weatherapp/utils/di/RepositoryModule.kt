package com.rizalzaenal.weatherapp.utils.di

import com.rizalzaenal.weatherapp.data.WeatherService
import com.rizalzaenal.weatherapp.data.local.FavoriteLocationsDao
import com.rizalzaenal.weatherapp.data.local.LatestLocationDao
import com.rizalzaenal.weatherapp.data.repository.LocationRepositoryImpl
import com.rizalzaenal.weatherapp.data.repository.WeatherRepositoryImpl
import com.rizalzaenal.weatherapp.domain.repository.LocationRepository
import com.rizalzaenal.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherService): WeatherRepository {
        return WeatherRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(
        service: WeatherService,
        favoriteLocationsDao: FavoriteLocationsDao,
        latestLocationDao: LatestLocationDao
    ): LocationRepository {
        return LocationRepositoryImpl(service, favoriteLocationsDao, latestLocationDao)
    }

}