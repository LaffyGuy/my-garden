package com.example.mygarden.core.exceptions

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ExceptionsModule {

    @Binds
    fun bindExceptionToMessageMapper(
        impl: ExceptionToMessageMapperImpl
    ): ExceptionToMessageMapper

}