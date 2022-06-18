package com.example.repaitshopapplication

import android.app.Application
import com.example.repaitshopapplication.database.AppDataBase
import com.example.repaitshopapplication.repository.ProductsRepository
import com.example.repaitshopapplication.repository.ProductsRepositoryImpl
import com.example.repaitshopapplication.ui.MainActivityViewModel
import com.example.repaitshopapplication.ui.product.ProductViewModel
import com.example.repaitshopapplication.ui.products.ProductsViewModel
import com.example.repaitshopapplication.ui.search.SearchFragment
import com.example.repaitshopapplication.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application(), KoinComponent {


    override fun onCreate() {
        super.onCreate()
        val database: AppDataBase = AppDataBase.getAppDatabase(this)
        val modules = module {
            viewModel { ProductsViewModel(get()) }
            viewModel { MainActivityViewModel(get()) }
            viewModel { ProductViewModel(get()) }
            viewModel { SearchViewModel(get()) }
            factory<ProductsRepository> { ProductsRepositoryImpl(database.productDao) }
        }

        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}