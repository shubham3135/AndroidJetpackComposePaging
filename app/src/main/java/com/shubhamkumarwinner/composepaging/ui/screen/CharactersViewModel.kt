package com.shubhamkumarwinner.composepaging.ui.screen

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shubhamkumarwinner.composepaging.data.models.Result
import com.shubhamkumarwinner.composepaging.data.paging.RickMortyPagingSource
import com.shubhamkumarwinner.composepaging.data.repository.RickRepo
import com.shubhamkumarwinner.composepaging.util.Constants.MAX_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val rickMortyPagingSource: RickMortyPagingSource
) : ViewModel() {
    val characters: Flow<PagingData<Result>> = Pager(PagingConfig(pageSize = MAX_PAGE_SIZE)){
        rickMortyPagingSource
    }.flow
}