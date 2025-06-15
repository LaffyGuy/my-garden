package com.example.mygarden.core.di

import com.example.mygarden.core.logger.DefaultLogger
import com.example.mygarden.core.logger.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    fun bindLogger(logger: DefaultLogger): Logger

}