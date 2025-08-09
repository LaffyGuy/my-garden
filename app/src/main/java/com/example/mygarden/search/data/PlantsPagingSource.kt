package com.example.mygarden.search.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mygarden.core.data.remote.PlantApi
import com.example.mygarden.core.exceptions.mapToAppException
import com.example.mygarden.search.domain.model.Plant



class PlantsPagingSource(
    private val loader: PlantApi,
    private val pageSize: Int,
    private val query: String?
) : PagingSource<Int, Plant>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Plant> {

        val pageIndex = params.key ?: 0

        return try {
            val response = loader.getListOfPlants(query = query, limit = pageSize, offset = pageIndex * pageSize)
            val plants = response.data.map { it.toDomain() }
            return LoadResult.Page(
                data = plants,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (plants.isNotEmpty()) pageIndex + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = mapToAppException(e)
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Plant>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition((anchorPosition)) ?: return null
        return page.prevKey?.plus(1) ?: page.prevKey?.minus(1)
    }

}
