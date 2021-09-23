package com.shubhamkumarwinner.composepaging.data.repository

import com.shubhamkumarwinner.composepaging.data.models.RickAndMorty
import com.shubhamkumarwinner.composepaging.data.remote.RickMortyApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class RickRepoImpl @Inject constructor(
    private val rickMortyApi: RickMortyApi
) : RickRepo{
    override suspend fun getAllCharacters(page: Int): RickAndMorty {
        delay(2000)
        return rickMortyApi.getAllCharacters(page)
    }
}