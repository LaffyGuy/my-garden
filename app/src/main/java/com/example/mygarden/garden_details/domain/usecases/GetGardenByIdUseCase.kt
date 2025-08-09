package com.example.mygarden.garden_details.domain.usecases

import com.example.mygarden.garden_details.domain.GardenDetailsRepository
import javax.inject.Inject

class GetGardenByIdUseCase @Inject constructor(private val gardenDetailsRepository: GardenDetailsRepository){

    suspend operator fun invoke(gardenId: Long) = gardenDetailsRepository.getGardenById(gardenId)

}