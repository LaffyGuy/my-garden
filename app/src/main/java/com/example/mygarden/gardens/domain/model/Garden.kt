package com.example.mygarden.gardens.domain.model

data class Garden(
    val id: Long = 0,
    val name: String,
    val description: String,
    val area: Double,
    val pointsJson: String,
    val imageUri: String,
    val createdAt: Long = System.currentTimeMillis()
)