package com.example.repaitshopapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val number: String,
    val date: ProductDate,
    val time: ProductTime,
    val problems: List<String>,
    val status: String
)