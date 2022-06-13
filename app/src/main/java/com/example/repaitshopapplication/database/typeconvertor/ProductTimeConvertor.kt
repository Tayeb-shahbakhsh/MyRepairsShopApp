package com.example.repaitshopapplication.database.typeconvertor

import androidx.room.TypeConverter
import com.example.repaitshopapplication.data.ProductTime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductTimeConvertor {

    @TypeConverter
    fun fromProductTime(productTime: ProductTime):String{
        val gson = Gson()
        val type = object : TypeToken<ProductTime>(){}.type
        return gson.toJson(productTime,type)
    }

    @TypeConverter
    fun toProductTime(productTime: String): ProductTime{
        val gson = Gson()
        val type = object : TypeToken<ProductTime>(){}.type
        return gson.fromJson(productTime,type)
    }
}