package com.example.repaitshopapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.database.typeconvertors.DateConvertor
import com.example.repaitshopapplication.database.typeconvertors.ListConvertor
import com.example.repaitshopapplication.database.typeconvertors.TimeConvertor

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(DateConvertor::class,TimeConvertor::class,ListConvertor::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        @JvmStatic
        private var database: AppDataBase? = null

        @JvmStatic
        fun getAppDatabase(context: Context): AppDataBase {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDataBase::class.java, "products.db")
                    .build()
            }
            return database as AppDataBase
        }
    }

    abstract val productDao: ProductDao
}