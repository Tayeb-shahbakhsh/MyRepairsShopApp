package com.example.repaitshopapplication.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productsRepository: ProductsRepository
    ) : ViewModel() {

    private var _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>>
        get() = _productsLiveData

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getAll().collectLatest { products ->
                _productsLiveData.postValue(products)
            }
        }
    }
}