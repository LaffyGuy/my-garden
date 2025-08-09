package com.example.mygarden.garden_configurator.data

import com.example.mygarden.gardens.data.model.GardenEntity
import com.example.mygarden.gardens.domain.model.Garden

fun GardenEntity.toDomain(): Garden {
    return Garden(
        id = id,
        name = name,
        description = description,
        area = 0.0,
        pointsJson = pointsJson,
        imageUri = imageUri,
        createdAt = createdAt
    )
}

fun Garden.toEntity(): GardenEntity {
    return GardenEntity(
        id = id,
        name = name,
        description = description,
        area = area,
        pointsJson = pointsJson,
        imageUri = imageUri,
        createdAt = createdAt
    )
}