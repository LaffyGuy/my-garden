package com.example.mygarden.garden_details.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.mygarden.R
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.garden_details.presentation.GardenDetailsUiState
import com.example.mygarden.garden_details.presentation.GardenDetailsViewModel

@Composable
fun GardenDetailsScreen() {

    val viewModel: GardenDetailsViewModel = hiltViewModel()
    val loadResult = viewModel.uiState.collectAsStateWithLifecycle()


    GardenDetailsContent(loadResult.value)
}

@Composable
fun GardenDetailsContent(
    loadResult: LoadResult<GardenDetailsUiState>
) {
    when (loadResult) {
        is LoadResult.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadResult.Success -> {
            val gardenDetails = loadResult.data
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Hello")
                if(gardenDetails.garden != null) {
                    AsyncImage(
                        model = gardenDetails.garden.imageUri,
                        contentDescription = stringResource(R.string.garden_image),
                        modifier = Modifier.height(150.dp).width(300.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = gardenDetails.garden.name)
                }

            }
        }
        is LoadResult.Error -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Something went wrong! Please try again later.")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GardenDetailsContentPreview() {
    GardenDetailsContent(LoadResult.Loading)
}