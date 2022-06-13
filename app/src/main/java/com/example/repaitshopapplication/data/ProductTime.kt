package com.example.repaitshopapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductTime(
    var hour : String,
    var minutes : String
) : Parcelable {

    fun getTime(): String{
        var hour = hour
        var minute = hour
        if (hour.length == 1){
            hour = "0$hour"
        }
        if (minute.length == 1){
            minute = "0$minute"
        }
        return "$hour:$minute"
    }
}
