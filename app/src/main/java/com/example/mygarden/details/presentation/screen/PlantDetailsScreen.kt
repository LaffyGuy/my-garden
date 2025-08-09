package com.example.mygarden.details.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mygarden.R
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.details.presentation.components.InfoItem
import com.example.mygarden.details.presentation.viewmodel.PlantDetailsUiState
import com.example.mygarden.details.presentation.viewmodel.PlantDetailsViewModel
import com.example.mygarden.ui.theme.AppTheme

@Composable
fun PlantDetailsScreen() {

    val viewModel = hiltViewModel<PlantDetailsViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getPlantDetails()
    }

    when(val result = state.value) {
        is LoadResult.Loading -> {
            CircularProgressIndicator()
        }
        is LoadResult.Success -> {
            PlantDetailsContent(state = result.data)
        }
        is LoadResult.Error -> {
            Text(text = "Error - ${result.exception.message}")
        }
    }




}

@Composable
fun PlantDetailsContent(
    state: PlantDetailsUiState
) {

    if(state.data != null) {
        val scrollState = rememberScrollState()
        val pagerState = rememberPagerState(pageCount = {
            5
        })
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .background(color = MaterialTheme.colorScheme.secondaryContainer),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                ,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center

                ) {
                    HorizontalPager(state = pagerState) { page ->
                        Image(
                            painterResource(
                                id = R.drawable.splash_icon
                            ),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = state.data.common_name ?: "",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(text = state.data.type ?: "")
                    }

                    Text(
                        text = state.data.description ?:  "",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Загальна інформація",
                        fontSize = 18.sp
                    )
                    InfoItem(label = "fdsdss", info = "dsdsd")
                    InfoItem(label = "dsdsds", info = "dsdsds")
                    InfoItem(label = "dsdsds", info = "dsdsds")
                    InfoItem(label = "dsdsdsd", info = "dsdsds")
                    InfoItem(label = "dsdsds", info = "dsdsd")

                    Text(
                        text = "Шкідники",
                        fontSize = 18.sp
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PlantDetailsPreview() {
    AppTheme {
        PlantDetailsContent(PlantDetailsUiState())
    }
}