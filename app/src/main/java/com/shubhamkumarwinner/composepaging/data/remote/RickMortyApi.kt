package com.shubhamkumarwinner.composepaging.data.remote

import com.shubhamkumarwinner.composepaging.data.models.RickAndMorty
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {
    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): RickAndMorty
}