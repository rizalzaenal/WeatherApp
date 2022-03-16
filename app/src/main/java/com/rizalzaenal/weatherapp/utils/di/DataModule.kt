package com.rizalzaenal.weatherapp.utils.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.rizalzaenal.weatherapp.data.WeatherService
import com.rizalzaenal.weatherapp.data.local.AppDatabase
import com.rizalzaenal.weatherapp.data.local.FavoriteLocationsDao
import com.rizalzaenal.weatherapp.data.local.LatestLocationDao
import com.rizalzaenal.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(
        @ApplicationContext appContext: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(appContext))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.create(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteLocationsDao(db: AppDatabase): FavoriteLocationsDao {
        return db.favoriteLocationsDao()
    }

    @Provides
    @Singleton
    fun provideLatestLocationDao(db: AppDatabase): LatestLocationDao {
        return db.latestLocationDao()
    }
}