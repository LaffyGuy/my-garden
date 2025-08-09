package com.example.mygarden.details.domain.usecase

import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.details.data.dto.PlantDetailsResponse
import com.example.mygarden.details.domain.repository.PlantDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantDetailsUseCase @Inject constructor(private val plantDetailsRepository: PlantDetailsRepository) {

    suspend operator fun invoke(plantId: Int): Flow<LoadResult<PlantDetailsResponse>> {
        return plantDetailsRepository.getPlantDetails(plantId)
    }

}