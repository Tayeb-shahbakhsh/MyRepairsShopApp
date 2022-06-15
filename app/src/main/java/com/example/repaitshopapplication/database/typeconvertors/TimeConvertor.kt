package com.example.repaitshopapplication.database.typeconvertors

import androidx.room.TypeConverter
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.data.ProductTime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TimeConvertor {

    @TypeConverter
    fun fromTime(productTime: ProductTime) : String =
        Gson().toJson(productTime,object :TypeToken<ProductTime>() {}.type)

    @TypeConverter
    fun toTime(productTime: String): ProductTime =
        Gson().fromJson(productTime,object :TypeToken<ProductTime>() {}.type)
}