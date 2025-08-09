package com.example.mygarden.gardens.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "garden")
data class GardenEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val area: Double,
    val pointsJson: String,
    val imageUri: String,
    val createdAt: Long = System.currentTimeMillis()
)
