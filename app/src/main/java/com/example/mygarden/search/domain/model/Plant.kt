package com.example.mygarden.search.domain.model

data class Plant(
    val id: Int,
    val name: String,
    val scientificName: String,
    val imageUrl: String,
    val genus: String,
    val family: String
)
