package com.example.repaitshopapplication.database.typeconvertors

import androidx.room.TypeConverter
import com.example.repaitshopapplication.data.ProductTime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConvertor {
    @TypeConverter
    fun fromTime(value: List<String>): String =
        Gson().toJson(value, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun toTime(value: String): List<String> =
        Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
}