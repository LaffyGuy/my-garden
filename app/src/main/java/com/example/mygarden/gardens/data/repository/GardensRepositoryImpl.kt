package com.example.mygarden.gardens.data.repository

import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.core.data.local.db.GardenDao
import com.example.mygarden.gardens.data.mapper.toGarden
import com.example.mygarden.gardens.data.model.GardenEntity
import com.example.mygarden.gardens.domain.model.Garden
import com.example.mygarden.gardens.domain.repository.GardenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GardensRepositoryImpl @Inject constructor(private val gardenDao: GardenDao): GardenRepository {

    override fun getAllGardens(): Flow<LoadResult<List<Garden>>> =
        gardenDao.getAllGardens()
            .map<List<GardenEntity>, LoadResult<List<Garden>>> { gardenEntities ->
                val gardens = gardenEntities.map { it.toGarden() }
                LoadResult.Success(gardens)
            }
            .onStart {
                emit(LoadResult.Loading)
            }
            .catch { e ->
                emit(LoadResult.Error(e as? Exception ?: Exception("Unknown error")))
            }
}