package com.pack.projectmanagementapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.services.local.ProductDatabase
import com.pack.projectmanagementapp.services.repo.ProductNetworkRepository
import com.pack.projectmanagementapp.services.repo.ProductRepository
import kotlinx.coroutines.launch


class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val productReposititory: ProductRepository
    private var networkRepo: ProductNetworkRepository
    private val _allProducts = MutableLiveData<List<Product>>()
    var allProducts: LiveData<List<Product>> = _allProducts

    init {
        val dao = ProductDatabase.getDatabase(application).getProductDao()
        networkRepo = ProductNetworkRepository()

        productReposititory = ProductRepository(dao, networkRepo)
        //getting the data from database
        allProducts = productReposititory.getAllProduct()
    }
    fun fetchDataFromApi() {
        Log.i("fetchDataFromApi","I am woking")
        productReposititory.fetchProductDetails { productList ->
            viewModelScope.launch {
                productList?.let { productReposititory.insert(it) }
            }
        }
    }
}