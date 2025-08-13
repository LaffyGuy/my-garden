package com.example.mygarden.key_feature.domain

import com.example.mygarden.key_feature.domain.model.KeyFeature

interface KeyFeatureRepository {

    suspend fun getKeyFeatures(): List<KeyFeature>

}