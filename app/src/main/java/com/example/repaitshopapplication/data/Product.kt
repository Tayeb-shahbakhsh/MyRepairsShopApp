package com.example.repaitshopapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var number: String = "",
    var date: ProductDate = ProductDate(0,0,0),
    var imagePath: String = "",
    var time: ProductTime=  ProductTime("0","0"),
    var problems: List<String> = listOf(""),
    var status: String = ""
) : Parcelable