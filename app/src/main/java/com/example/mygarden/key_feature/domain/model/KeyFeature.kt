package com.example.mygarden.key_feature.domain.model

import com.example.mygarden.core.data.enteties.ImageSource

data class KeyFeature(
    val id: Long,
    val title: String,
    val description: String,
    val image: ImageSource
)
