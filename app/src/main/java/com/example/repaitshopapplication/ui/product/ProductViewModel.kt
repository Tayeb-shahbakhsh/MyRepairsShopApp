package com.example.repaitshopapplication.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductsRepository) : ViewModel() {

//    val _productsLiveData = MutableLiveData<List<Product>>()
//    val _productLiveData = MutableLiveData<Product>()

//    val productLiveData: LiveData<Product>
//        get() = _productLiveData
//    val productsLiveData: LiveData<List<Product>>
//        get() = _productsLiveData
//
//    fun getProduct(position: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _productLiveData.postValue(productsLiveData.value?.get(position))
//        }
//    }
//
//    fun getAllProducts() {
//        viewModelScope.launch {
//            repository.getAll().collectLatest {
//                _productsLiveData.postValue(it)
//            }
//        }
//    }

    fun removeProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(product)
        }
    }

    fun updateProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(product)
        }
    }

}