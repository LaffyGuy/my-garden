package com.example.mygarden.key_feature.data.repository

import com.example.mygarden.R
import com.example.mygarden.core.data.enteties.ImageSource
import com.example.mygarden.key_feature.domain.KeyFeatureRepository
import com.example.mygarden.key_feature.domain.model.KeyFeature
import javax.inject.Inject

class KeyFeatureRepositoryImpl @Inject constructor(): KeyFeatureRepository {


    override suspend fun getKeyFeatures(): List<KeyFeature> {
        return listOf(
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
        )
    }
}