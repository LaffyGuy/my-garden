package com.example.mygarden.gardens.di

import com.example.mygarden.gardens.data.repository.GardensRepositoryImpl
import com.example.mygarden.gardens.domain.repository.GardenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GardensModule {

    @Binds
    fun bindGardenRepository(gardensRepositoryImpl: GardensRepositoryImpl): GardenRepository

}