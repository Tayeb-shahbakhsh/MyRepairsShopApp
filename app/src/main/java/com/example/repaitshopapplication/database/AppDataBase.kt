package com.example.repaitshopapplication.database

import android.content.Context
import androidx.room.*
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.database.typeconvertor.ProductDataConvertor
import com.example.repaitshopapplication.database.typeconvertor.ProductTimeConvertor


@Database(version = 1, exportSchema = false, entities = [Product::class])
@TypeConverters(ProductDataConvertor::class,ProductTimeConvertor::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @JvmStatic
        private var database: AppDatabase? = null

        @JvmStatic
        fun getAppDatabase(context: Context): AppDatabase {
            if (database == null)
                database = Room.databaseBuilder(context, AppDatabase::class.java, "products.db")
                    .build()
            return database as AppDatabase
        }
    }

    abstract val productsDao: ProductsDao
}