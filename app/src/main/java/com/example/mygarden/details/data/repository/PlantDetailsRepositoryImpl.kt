package com.example.mygarden.details.data.repository

import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.core.data.remote.PlantApi
import com.example.mygarden.details.data.dto.PlantDetailsResponse
import com.example.mygarden.details.domain.repository.PlantDetailsRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject
import kotlin.Exception

class PlantDetailsRepositoryImpl @Inject constructor(private val plantApi: PlantApi): PlantDetailsRepository {

    override suspend fun getPlantDetails(plantId: Int): Flow<LoadResult<PlantDetailsResponse>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val response = plantApi.getPlantDetails(plantId)
                emit(LoadResult.Success(response))
            } catch (e: IOException) {
                emit(LoadResult.Error(Exception("Перевірте інтернет з'єднання")))
            } catch (e: JsonSyntaxException) {
                emit(LoadResult.Error(Exception("Помилка парсингу JSON")))
            } catch (e: Exception) {
                emit(LoadResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}