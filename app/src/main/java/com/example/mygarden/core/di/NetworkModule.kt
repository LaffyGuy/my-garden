package com.example.mygarden.core.di

import com.example.mygarden.core.data.remote.PlantApi
import com.example.mygarden.details.data.repository.PlantDetailsRepositoryImpl
import com.example.mygarden.details.domain.repository.PlantDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = "https://perenual.com/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePlantApi(retrofit: Retrofit): PlantApi {
        return retrofit.create(PlantApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlantDetailsRepository(plantApi: PlantApi): PlantDetailsRepository {
        return PlantDetailsRepositoryImpl(plantApi)
    }

}