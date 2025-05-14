package com.example.mygarden.gardens.domain.model

data class Garden(
    val id: Long,
    val name: String,
    val description: String,
    val imageUri: String,
    val date: Long
)