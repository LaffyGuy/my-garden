package com.example.mygarden.details.domain.repository

import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.details.data.dto.PlantDetailsResponse
import kotlinx.coroutines.flow.Flow

interface PlantDetailsRepository {

    suspend fun getPlantDetails(plantId: Int): Flow<LoadResult<PlantDetailsResponse>>

}