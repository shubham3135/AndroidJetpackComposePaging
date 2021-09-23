package com.shubhamkumarwinner.composepaging.data.repository

import com.shubhamkumarwinner.composepaging.data.models.RickAndMorty

interface RickRepo {
    suspend fun getAllCharacters(page: Int): RickAndMorty
}