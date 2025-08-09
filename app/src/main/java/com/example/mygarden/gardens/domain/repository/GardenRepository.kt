package com.example.mygarden.gardens.domain.repository

import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.gardens.domain.model.Garden
import kotlinx.coroutines.flow.Flow

interface GardenRepository {

    fun getAllGardens(): Flow<LoadResult<List<Garden>>>
}