package com.example.repaitshopapplication.database

import androidx.room.*
import com.example.repaitshopapplication.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Insert
    fun add(product : Product) : Long

    @Update
    fun update(product: Product) : Int

    @Delete
    fun delete(product : Product) : Int

    @Query("SELECT * FROM products ORDER BY id DESC")
    fun getAll() : Flow<List<Product>>
}