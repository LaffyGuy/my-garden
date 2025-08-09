package com.example.mygarden.garden_configurator.domain.repository

import com.example.mygarden.gardens.domain.model.Garden

interface GardenConfiguratorRepository {

    suspend fun addNewGarden(newGarden: Garden)

}