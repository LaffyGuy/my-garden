package com.example.mygarden.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mygarden.gardens.data.model.GardenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenDao {

    @Query("SELECT * FROM garden")
    fun getAllGardens(): Flow<List<GardenEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewGarden(gardenEntity: GardenEntity)

    @Query("SELECT * FROM garden WHERE id = :gardenId")
    suspend fun getGardenById(gardenId: Long): GardenEntity
}