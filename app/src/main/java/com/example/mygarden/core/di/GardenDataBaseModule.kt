package com.example.mygarden.core.di

import android.app.Application
import androidx.room.Room
import com.example.mygarden.gardens.data.source.GardenDao
//import com.example.mygarden.core.data.db.GardenDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object GardenDataBaseModule {
//
//    @Provides
//    @Singleton
//    fun provideGardenDataBase(context: Application): GardenDataBase {
//        return Room.databaseBuilder(
//            context,
//            GardenDataBase::class.java,
//            "garden.db"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideGardenDao(gardenDataBase: GardenDataBase): GardenDao {
//        return gardenDataBase.getDao()
//    }
//
//
//}