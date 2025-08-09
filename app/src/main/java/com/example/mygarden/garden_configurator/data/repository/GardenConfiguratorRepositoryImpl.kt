package com.example.mygarden.garden_configurator.data.repository

import com.example.mygarden.core.data.local.db.GardenDao
import com.example.mygarden.garden_configurator.domain.repository.GardenConfiguratorRepository
import com.example.mygarden.gardens.data.mapper.toGardenEntity
import com.example.mygarden.gardens.domain.model.Garden
import javax.inject.Inject


class GardenConfiguratorRepositoryImpl @Inject constructor(private val gardenDao: GardenDao): GardenConfiguratorRepository {


    override suspend fun addNewGarden(newGarden: Garden) {
        gardenDao.addNewGarden(newGarden.toGardenEntity())
    }
}