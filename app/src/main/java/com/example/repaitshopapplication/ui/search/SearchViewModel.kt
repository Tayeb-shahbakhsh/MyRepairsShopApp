package com.example.repaitshopapplication.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val _filteredProductsLiveDate = MutableLiveData<List<Product>?>()
    private val _productsLiveData = MutableLiveData<List<Product>>()
    val filteredProductsLiveDate: MutableLiveData<List<Product>?>
        get() = _filteredProductsLiveDate
    val productLiveData: LiveData<List<Product>>
        get() = _productsLiveData

    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().collectLatest { products ->
                _productsLiveData.postValue(products)
            }
        }
    }

    fun getProduct(id : Int) : Product{
        var product :Product = Product()

        productLiveData.value?.forEach {
            if (it.id == id){
                product = it
            }
        }

        return product
    }

    fun findProductFromDataBase(text: String) {
        viewModelScope.launch(Dispatchers.IO) {

            var newList = productLiveData.value?.filter { product ->
                product.name.contains(text)
            }

            _filteredProductsLiveDate.postValue(newList)
        }
    }
}



