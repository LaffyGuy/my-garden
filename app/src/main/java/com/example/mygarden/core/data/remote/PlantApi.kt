package com.example.mygarden.core.data.remote

import com.example.mygarden.BuildConfig
import com.example.mygarden.details.data.dto.PlantDetailsResponse
import com.example.mygarden.search.data.dto.PlantsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantApi {

    @GET("species/details/{ID}")
    suspend fun getPlantDetails(
        @Path("ID") id: Int,
        @Query("key") apiKey: String =BuildConfig.API_KEY
    ): PlantDetailsResponse

    @GET("species-list")
    suspend fun getListOfPlants(
        @Query("key") apiKeyString: String = BuildConfig.API_KEY,
        @Query("q") query: String? = null,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PlantsList
}