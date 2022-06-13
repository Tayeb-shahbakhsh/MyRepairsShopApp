package com.example.repaitshopapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Product(
    val name: String,
    val number: Long,
    val date: ProductDate,
    val time: ProductTime,
    val status: String
)