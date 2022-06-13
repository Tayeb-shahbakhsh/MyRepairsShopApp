package com.example.repaitshopapplication.database.typeconvertor

import androidx.room.TypeConverter
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.data.ProductDate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductDataConvertor {
    @TypeConverter
    fun fromProductDate(productDate: ProductDate): String {
        val gson = Gson()
        val type = object : TypeToken<ProductDate>() {}.type
        return gson.toJson(productDate, type)
    }

    @TypeConverter
    fun toProductDate(productDate: String): ProductDate {
        val gson = Gson()
        val type = object : TypeToken<ProductDate>() {}.type
        return gson.fromJson(productDate, type)
    }
}