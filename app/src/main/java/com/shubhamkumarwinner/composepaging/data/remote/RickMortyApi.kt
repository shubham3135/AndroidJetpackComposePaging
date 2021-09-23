package com.shubhamkumarwinner.composepaging.data.remote

import com.shubhamkumarwinner.composepaging.data.models.Result
import com.shubhamkumarwinner.composepaging.data.models.RickAndMorty
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyApi {
    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): RickAndMorty

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Result
}