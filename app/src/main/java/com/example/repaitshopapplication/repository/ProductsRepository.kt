package com.example.repaitshopapplication.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.repaitshopapplication.data.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun add(product : Product) : Long

    fun update(product: Product) : Int

    fun delete(product : Product) : Int

    fun getAll() : Flow<List<Product>>
}