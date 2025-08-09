package com.example.mygarden.garden_configurator.di

import com.example.mygarden.garden_configurator.data.repository.GardenConfiguratorRepositoryImpl
import com.example.mygarden.garden_configurator.domain.repository.GardenConfiguratorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GardenConfiguratorModule {

    @Binds
    fun bindGardenConfiguratorRepository(gardenConfiguratorRepositoryImpl: GardenConfiguratorRepositoryImpl): GardenConfiguratorRepository


}