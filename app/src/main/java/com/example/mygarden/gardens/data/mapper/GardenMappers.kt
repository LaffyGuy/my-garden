package com.example.mygarden.gardens.data.mapper

import com.example.mygarden.gardens.data.model.GardenEntity
import com.example.mygarden.gardens.domain.model.Garden

fun GardenEntity.toGarden(): Garden {
    return Garden(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUri = this.imageUri,
        date = this.date
    )
}

fun Garden.toGardenEntity(): GardenEntity {
    return GardenEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUri = this.imageUri,
        date = this.date
    )
}