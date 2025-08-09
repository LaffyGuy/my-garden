package com.example.mygarden.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mygarden.core.data.remote.PlantApi
import com.example.mygarden.search.data.PlantsPagingSource
import com.example.mygarden.search.domain.model.Plant
import com.example.mygarden.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val plantApi: PlantApi): SearchRepository {

    override fun getListOfSearchPlants(query: String): Flow<PagingData<Plant>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PlantsPagingSource(plantApi, pageSize = 20, query) }
        ).flow
    }
}