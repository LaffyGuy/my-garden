package com.example.mygarden.search.data.dto

data class PlantsList(
    val current_page: Int,
    val data: List<Data>,
    val from: Int,
    val last_page: Int,
    val per_page: Int,
    val to: Int,
    val total: Int
)