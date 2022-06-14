package com.example.repaitshopapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.data.ProductDate
import com.example.repaitshopapplication.repository.ProductsRepository
import com.example.repaitshopapplication.service.PhotoCapture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.nio.file.Path

class MainActivityViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private var _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>>
        get() = _productsLiveData

    val newProductDateLiveData = MutableLiveData<ProductDate>()
    val photoPathLiveData = MutableLiveData<Path>()

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

    fun capturePhoto(mainActivity: MainActivity) {
        val photoPath = PhotoCapture().createImageFile(mainActivity)
    }


}