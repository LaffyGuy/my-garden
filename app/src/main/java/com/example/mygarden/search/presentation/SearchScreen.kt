package com.example.mygarden.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mygarden.search.domain.model.Plant
import com.example.mygarden.search.presentation.components.PlantItem

@Composable
fun SearchScreen() {

    val viewModel: SearchViewModel = hiltViewModel()

    val plants = viewModel.plantsFlow.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(plants.loadState) {
        viewModel.onLoadStateChange(plants.loadState)
    }
    SearchContent(
        plants = plants,
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        searchQuery = uiState.searchQuery,
        onSearchQueryChanged = { newQuery ->
            viewModel.onSearchQueryChanged(newQuery)
        }
    )

}

@Composable
fun SearchContent(
    plants: LazyPagingItems<Plant>,
    isLoading: Boolean,
    errorMessage: String?,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            placeholder = { Text("Пошук рослин...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
        )


        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }

        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(plants.itemCount) { index ->
                plants[index]?.let { plant ->
                    PlantItem(
                        name = plant.name,
                        scientificName = plant.scientificName,
                        imageUrl = plant.imageUrl
                    )
                }
            }
            when (plants.loadState.append) {
                is LoadState.Loading -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is LoadState.Error -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = "Помилка при довантаженні",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                else -> Unit
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun SearchContentPreview() {
//    SearchContent()
}