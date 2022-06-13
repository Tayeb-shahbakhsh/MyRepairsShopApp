package com.example.repaitshopapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val number: Int,
    val date: ProductDate,
    val time: ProductTime,
    val status: String
) : Parcelable