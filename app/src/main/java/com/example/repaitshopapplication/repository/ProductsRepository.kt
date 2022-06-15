package com.example.repaitshopapplication.repository

import com.example.repaitshopapplication.data.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun add(product : Product) : Long

    fun update(product: Product) : Int

    fun delete(product : Product) : Int

    fun getAll() : Flow<List<Product>>
}