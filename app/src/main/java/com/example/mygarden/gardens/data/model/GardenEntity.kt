package com.example.mygarden.gardens.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "garden")
data class GardenEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String,
    val imageUri: String,
    val date: Long
)
