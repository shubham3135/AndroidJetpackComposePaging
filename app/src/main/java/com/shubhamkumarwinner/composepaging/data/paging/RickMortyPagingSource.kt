package com.shubhamkumarwinner.composepaging.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shubhamkumarwinner.composepaging.data.models.Result
import com.shubhamkumarwinner.composepaging.data.repository.RickRepo
import javax.inject.Inject

class RickMortyPagingSource @Inject constructor(private val rickRepo: RickRepo): PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val pageNumber = params.key ?: 0
            val charactersResponse = rickRepo.getAllCharacters(page = pageNumber)
            val characters = charactersResponse.results

            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = pageNumber + 1

            LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}