package com.example.mygarden.search.data

import com.example.mygarden.search.data.dto.Data
import com.example.mygarden.search.domain.model.Plant

fun Data.toDomain(): Plant {
    return Plant(
        id = id,
        name = common_name ?: "No name",
        scientificName = scientific_name?.firstOrNull() ?: "Unknown",
        imageUrl = default_image?.medium_url ?: "",
        genus = genus ?: "Unknown",
        family = family ?: "Unknown"
    )
}