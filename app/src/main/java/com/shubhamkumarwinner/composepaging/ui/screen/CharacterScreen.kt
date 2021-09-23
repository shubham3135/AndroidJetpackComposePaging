package com.shubhamkumarwinner.composepaging.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.shubhamkumarwinner.composepaging.data.models.Result
import com.shubhamkumarwinner.composepaging.ui.components.CharacterItem
import com.shubhamkumarwinner.composepaging.ui.components.ErrorItem
import com.shubhamkumarwinner.composepaging.ui.components.RickMortyAppBar
import kotlinx.coroutines.flow.Flow

@ExperimentalCoilApi
@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val charactersList: Flow<PagingData<Result>> = viewModel.characters
    val lazyPagingItems = charactersList.collectAsLazyPagingItems()
    Surface {
        Scaffold(topBar = {
            RickMortyAppBar(title = {
                Text("Rick & Morty")
            },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }) { innerPadding ->
            LazyColumn(modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)){
                items(lazyPagingItems) { item ->
                    if (item != null) {
                        CharacterItem(
                            character = item,
                        )
                    }
                }
                lazyPagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier.fillParentMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item { CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            ) }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val e = lazyPagingItems.loadState.refresh as LoadState.Error
                            item {
                                ErrorItem(
                                    message = e.error.localizedMessage!!,
                                    modifier = Modifier.fillParentMaxSize(),
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val e = lazyPagingItems.loadState.append as LoadState.Error
                            item {
                                ErrorItem(
                                    message = e.error.localizedMessage!!,
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}
