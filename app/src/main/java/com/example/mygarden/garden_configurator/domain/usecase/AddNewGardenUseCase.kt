package com.example.mygarden.garden_configurator.domain.usecase

import com.example.mygarden.garden_configurator.domain.repository.GardenConfiguratorRepository
import com.example.mygarden.gardens.domain.model.Garden
import javax.inject.Inject

class AddNewGardenUseCase @Inject constructor(private val gardenConfiguratorRepository: GardenConfiguratorRepository) {

    suspend fun invoke(newGarden: Garden) {
        gardenConfiguratorRepository.addNewGarden(newGarden)
    }

}