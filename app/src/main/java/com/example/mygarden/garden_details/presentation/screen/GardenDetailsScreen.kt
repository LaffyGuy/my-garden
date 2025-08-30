package com.example.mygarden.garden_details.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mygarden.R
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.core.data.enteties.ImageSource
import com.example.mygarden.core.presentation.components.ImageView
import com.example.mygarden.core.utils.PaddingSize
import com.example.mygarden.core.utils.TextSize
import com.example.mygarden.core.utils.formatDate
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
            SuccessGardenDetailsContent(loadResult.data)
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

@Composable
fun SuccessGardenDetailsContent(
    state: GardenDetailsUiState
) {
    val scrollState = rememberScrollState()
    Column(
       verticalArrangement = Arrangement.Top,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier
           .padding(PaddingSize.Small)
           .verticalScroll(state = scrollState)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = state.garden?.name ?: "",
                    fontSize = TextSize.Large,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "Area: ${state.garden?.area}")
            }

            Text(text = formatDate(state.garden?.createdAt ?: 0L))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ImageView(
            imageSource = ImageSource.UriString(uri = state.garden?.imageUri ?: ""),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.description),
            fontSize = TextSize.Large,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Text(
                text = state.garden?.description ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(PaddingSize.Small)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.plants),
            fontSize = TextSize.Large,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Text(
                text = "Some text about plants...",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(PaddingSize.Small)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GardenDetailsContentPreview() {
    GardenDetailsContent(LoadResult.Loading)
}