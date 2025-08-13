package com.example.mygarden.key_feature.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mygarden.R
import com.example.mygarden.core.data.enteties.ImageSource
import com.example.mygarden.core.navigation.GardensGraph
import com.example.mygarden.core.navigation.KeyFeatureRoute
import com.example.mygarden.core.navigation.LocalNavController
import com.example.mygarden.core.presentation.components.ImageView
import com.example.mygarden.key_feature.domain.model.KeyFeature
import com.example.mygarden.key_feature.presentation.KeyFeatureViewModel

@Composable
fun KeyFeatureScreen() {

    val navController = LocalNavController.current

    val viewModel: KeyFeatureViewModel = hiltViewModel()

    val keyFeatures by viewModel.keyFeature.collectAsStateWithLifecycle()

    KeyFeaturesContent(
        keyFeatures = keyFeatures,
        navigateToGardens = {
              navController.navigate(GardensGraph.GardensRoute) {
                  popUpTo<KeyFeatureRoute> {
                      inclusive = true
                  }
              }
        }
    )

}

@Composable
fun KeyFeaturesContent(
    keyFeatures: List<KeyFeature>,
    navigateToGardens: () -> Unit
) {

    val pagerState = rememberPagerState(initialPage = 0) {
        keyFeatures.size
    }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) { page ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val feature = keyFeatures[page]
            Text(text = feature.title)
            Spacer(modifier = Modifier.height(16.dp))
            ImageView(imageSource = feature.image, modifier = Modifier.size(150.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = feature.description, textAlign = TextAlign.Center)
            if (page == 2) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = navigateToGardens
                ) {
                    Text(text = "Let's start !")
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun KeyFeatureContentPreview() {
    KeyFeaturesContent(
        keyFeatures = listOf(
            KeyFeature(
                id = 1L,
                title = "Plot configurator",
                description = "Create a virtual map of your garden or vegetable patch: draw plots, measure the area, and plan the placement of plants.",
                image = ImageSource.DrawableRes(R.drawable.key_feature_garden_configurator)
            ),
            KeyFeature(
                id = 2L,
                title = "Catalog of plants",
                description = "Browse a large list of plants with images and basic information to easily choose the right ones for your garden.",
                image = ImageSource.DrawableRes(R.drawable.key_feature_plants_list)
            ),
            KeyFeature(
                id = 3L,
                title = "Detailed information",
                description = "Learn everything about each plant: growing conditions, seasonality, watering, care, and much more.",
                image = ImageSource.DrawableRes(R.drawable.key_feature_plant_details)
            )
        ),
        navigateToGardens = {}
    )
}