package com.example.mygarden.search.domain.usecase

import androidx.paging.PagingData
import com.example.mygarden.search.domain.model.Plant
import com.example.mygarden.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPlantsUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(query: String): Flow<PagingData<Plant>> = searchRepository.getListOfSearchPlants(query)

}