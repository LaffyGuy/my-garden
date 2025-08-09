package com.example.mygarden.gardens.presentation.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.core.navigation.GardensGraph
import com.example.mygarden.core.navigation.LocalNavController
import com.example.mygarden.gardens.domain.model.Garden
import com.example.mygarden.gardens.presentation.GardensViewModel
import com.example.mygarden.gardens.presentation.components.GardenItem
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GardensScreen() {


    val viewModel: GardensViewModel = hiltViewModel()

    val state by viewModel.gardensState.collectAsStateWithLifecycle()

    val navController = LocalNavController.current

    val permissionState = rememberPermissionState(
        android.Manifest.permission.READ_MEDIA_IMAGES
    )

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            viewModel.refreshGardens()
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    GardensContent(
        loadResult = state,
        tryAgainAction = {
            viewModel.refreshGardens()
        },
        navigateToGardenDetails = { gardenId ->
            navController.navigate(GardensGraph.GardenDetailsRoute(gardenId))
        }

    )



}

@Composable
fun GardensContent(
    loadResult: LoadResult<List<Garden>>,
    tryAgainAction: () -> Unit,
    navigateToGardenDetails: (gardenId: Long) -> Unit
) {


    when(loadResult){
        is LoadResult.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadResult.Success -> {
            Log.d("MyTag", "Data - ${loadResult.data[1]}")
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(loadResult.data) { garden ->
                    GardenItem(
                        name = garden.name,
                        description = garden.description,
                        imageUri = garden.imageUri,
                        modifier = Modifier.clickable {
                            navigateToGardenDetails(garden.id)
                        }
                    )
                }
            }
        }
        is LoadResult.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Something went wrong, please try again.")
                Button(
                    onClick = tryAgainAction
                ) {
                    Text("Try Again")
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GardensPreview() {
    GardensContent(
        loadResult = LoadResult.Loading,
        tryAgainAction = {},
        navigateToGardenDetails = {}
    )
}
