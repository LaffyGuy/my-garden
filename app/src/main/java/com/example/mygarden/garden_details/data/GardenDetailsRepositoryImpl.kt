package com.example.mygarden.garden_details.data

import com.example.mygarden.core.data.local.db.GardenDao
import com.example.mygarden.garden_configurator.data.toDomain
import com.example.mygarden.garden_details.domain.GardenDetailsRepository
import com.example.mygarden.gardens.domain.model.Garden
import javax.inject.Inject

class GardenDetailsRepositoryImpl @Inject constructor(private val gardenDao: GardenDao): GardenDetailsRepository {

    override suspend fun getGardenById(gardenId: Long): Garden {
        return gardenDao.getGardenById(gardenId).toDomain()
    }
}