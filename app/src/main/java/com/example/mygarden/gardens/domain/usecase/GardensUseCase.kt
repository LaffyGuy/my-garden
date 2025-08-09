package com.example.mygarden.gardens.domain.usecase

import com.example.mygarden.gardens.domain.repository.GardenRepository
import javax.inject.Inject

class GardensUseCase @Inject constructor(private val gardenRepository: GardenRepository) {

    operator fun invoke() = gardenRepository.getAllGardens()
}