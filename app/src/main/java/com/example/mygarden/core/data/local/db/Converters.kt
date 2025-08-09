package com.example.mygarden.core.data.local.db

import androidx.compose.ui.geometry.Offset
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromOffsetList(value: List<Offset>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOffsetList(value: String): List<Offset> {
        val type = object : TypeToken<List<Offset>>() {}.type
        return gson.fromJson(value, type)
    }

}