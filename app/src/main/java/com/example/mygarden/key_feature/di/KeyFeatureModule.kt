package com.example.mygarden.key_feature.di

import com.example.mygarden.key_feature.data.repository.KeyFeatureRepositoryImpl
import com.example.mygarden.key_feature.domain.KeyFeatureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface KeyFeatureModule {

    @Binds
    fun bindKeyFeatureRepository(
        keyFeatureRepositoryImpl: KeyFeatureRepositoryImpl
    ): KeyFeatureRepository

}