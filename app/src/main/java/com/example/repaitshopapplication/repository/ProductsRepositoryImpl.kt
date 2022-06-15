package com.example.repaitshopapplication.repository

import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.database.ProductDao
import kotlinx.coroutines.flow.Flow

class ProductsRepositoryImpl(private val productsDao: ProductDao) : ProductsRepository {

    override fun add(product: Product): Long = productsDao.add(product)

    override fun update(product: Product): Int = productsDao.update(product)

    override fun delete(product: Product): Int = productsDao.delete(product)

    override fun getAll(): Flow<List<Product>> = productsDao.getAll()
}