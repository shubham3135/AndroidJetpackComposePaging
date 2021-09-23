package com.shubhamkumarwinner.composepaging.di

import com.shubhamkumarwinner.composepaging.data.remote.RickMortyApi
import com.shubhamkumarwinner.composepaging.data.repository.RickRepo
import com.shubhamkumarwinner.composepaging.data.repository.RickRepoImpl
import com.shubhamkumarwinner.composepaging.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesRickMortyApi(): RickMortyApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RickMortyApi::class.java)

    @Provides
    @Singleton
    fun provideCoinRepository(api: RickMortyApi):RickRepo = RickRepoImpl(api)
}