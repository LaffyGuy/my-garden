package com.example.mygarden.garden_details.domain

import com.example.mygarden.gardens.domain.model.Garden

interface GardenDetailsRepository {

    suspend fun getGardenById(gardenId: Long): Garden

}