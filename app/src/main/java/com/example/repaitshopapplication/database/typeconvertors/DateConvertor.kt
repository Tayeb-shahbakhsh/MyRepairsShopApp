package com.example.repaitshopapplication.database.typeconvertors

import androidx.room.TypeConverter
import com.example.repaitshopapplication.data.ProductDate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DateConvertor() {
    @TypeConverter
    fun fromDate(productDate: ProductDate) : String =
        Gson().toJson(productDate, object : TypeToken<ProductDate>() {}.type)

    @TypeConverter
    fun toDate(productDate: String) : ProductDate =
        Gson().fromJson(productDate, object : TypeToken<ProductDate>() {}.type)
}