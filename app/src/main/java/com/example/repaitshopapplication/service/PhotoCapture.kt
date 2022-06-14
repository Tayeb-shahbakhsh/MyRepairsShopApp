package com.example.repaitshopapplication.service

import android.content.Context
import android.os.Environment
import androidx.core.app.ActivityCompat.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PhotoCapture {

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String



    @Throws(IOException::class)
    fun createImageFile(context: Context): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDirs()
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }


}