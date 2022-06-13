package com.example.repaitshopapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.repaitshopapplication.data.Product


@Database(version = 1, exportSchema = false, entities = [Product::class])
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @JvmStatic
        private var database: AppDatabase? = null

        @JvmStatic
        fun getAppDatabase(context: Context): AppDatabase {
            if (database == null)
                database = Room.databaseBuilder(context, AppDatabase::class.java, "products.db")
                    .allowMainThreadQueries().build()
            return database as AppDatabase
        }
    }

    abstract val productsDao: ProductsDao
}