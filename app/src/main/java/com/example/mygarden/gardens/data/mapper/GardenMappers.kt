package com.example.mygarden.gardens.data.mapper

import com.example.mygarden.gardens.data.model.GardenEntity
import com.example.mygarden.gardens.domain.model.Garden

fun GardenEntity.toGarden(): Garden {
    return Garden(
        id = id,
        name = name,
        description = description,
        area = area,
        pointsJson = pointsJson,
        imageUri = imageUri,
        createdAt = createdAt
    )
}

fun Garden.toGardenEntity(): GardenEntity {
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