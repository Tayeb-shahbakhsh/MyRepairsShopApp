package com.example.repaitshopapplication

import android.app.Application
import androidx.room.Database
import com.example.repaitshopapplication.database.AppDatabase
import com.example.repaitshopapplication.repository.ProductsRepository
import com.example.repaitshopapplication.repository.ProductsRepositoryImpl
import com.example.repaitshopapplication.ui.MainActivityViewModel
import com.example.repaitshopapplication.ui.product.ProductsViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application(), KoinComponent {


    override fun onCreate() {
        super.onCreate()
        val database: AppDatabase = AppDatabase.getAppDatabase(this)
        val modules = module {
            viewModel { ProductsViewModel(get()) }
            viewModel { MainActivityViewModel(get()) }
            factory<ProductsRepository> { ProductsRepositoryImpl(database.productsDao) }
        }

        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}