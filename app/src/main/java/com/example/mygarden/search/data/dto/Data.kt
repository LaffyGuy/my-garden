package com.example.mygarden.search.data.dto

data class Data(
    val authority: Any?,
    val common_name: String?,
    val cultivar: String?,
    val default_image: DefaultImage?,
    val family: String?,
    val genus: String?,
    val hybrid: Any?,
    val id: Int,
    val other_name: List<String>?,
    val scientific_name: List<String>?,
    val species_epithet: String?,
    val subspecies: Any?,
    val variety: Any?
)