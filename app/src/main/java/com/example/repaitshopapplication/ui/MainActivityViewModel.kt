package com.example.repaitshopapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.data.ProductDate
import com.example.repaitshopapplication.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import saman.zamani.persiandate.PersianDate
import java.nio.file.Path

class MainActivityViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private var _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>>
        get() = _productsLiveData

    val newProductDateLiveData = MutableLiveData<ProductDate>(ProductDate(PersianDate().shYear,PersianDate().shMonth,PersianDate().shDay))
    val photoPathLiveData = MutableLiveData<String>("")

    fun addProducts(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.add(product)
        }
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getAll().collectLatest { products ->
                _productsLiveData.postValue(products)
            }
        }
    }

}