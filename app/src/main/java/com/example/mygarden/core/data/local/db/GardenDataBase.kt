package com.example.mygarden.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mygarden.gardens.data.model.GardenEntity

@Database(entities = [GardenEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class GardenDataBase: RoomDatabase() {

    abstract fun getDao(): GardenDao

}