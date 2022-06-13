package com.example.repaitshopapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import saman.zamani.persiandate.PersianDate

@Parcelize
data class ProductDate(
    val year : Int,
    val month : Int,
    val day : Int,
) : Parcelable{

    fun getDate(): String {
        val monthName = PersianDate().monthName(month,PersianDate.Dialect.IRANIAN)
        return "$day $monthName $year"
    }
}

