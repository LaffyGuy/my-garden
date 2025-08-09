package com.example.mygarden.search.di

import com.example.mygarden.search.data.repository.SearchRepositoryImpl
import com.example.mygarden.search.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SearchPlantModule {

    @Binds
    fun bindSearchRepository(
        impl: SearchRepositoryImpl
    ): SearchRepository

}