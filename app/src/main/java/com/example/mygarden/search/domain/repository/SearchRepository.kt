package com.example.mygarden.search.domain.repository

import androidx.paging.PagingData
import com.example.mygarden.search.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getListOfSearchPlants(query: String): Flow<PagingData<Plant>>

}