package com.example.mygarden.garden_details.di

import com.example.mygarden.garden_details.data.GardenDetailsRepositoryImpl
import com.example.mygarden.garden_details.domain.GardenDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GardenDetailsModule {

    @Binds
    fun bindGardenDetailsRepository(gardenDetailsRepositoryImpl: GardenDetailsRepositoryImpl): GardenDetailsRepository

}