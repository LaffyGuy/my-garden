package com.example.mygarden.core.di

import android.app.Application
import androidx.room.Room
import com.example.mygarden.core.data.local.db.GardenDao
import com.example.mygarden.core.data.local.db.GardenDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GardenDataBaseModule {

    @Provides
    @Singleton
    fun provideGardenDataBase(context: Application): GardenDataBase {
        return Room.databaseBuilder(
                context,
                GardenDataBase::class.java,
                "garden.db"
            ).fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideGardenDao(gardenDataBase: GardenDataBase): GardenDao {
        return gardenDataBase.getDao()
    }


}