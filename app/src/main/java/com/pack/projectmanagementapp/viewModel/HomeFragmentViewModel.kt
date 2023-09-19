package com.pack.projectmanagementapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycling
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.services.local.ProductDatabase
import com.pack.projectmanagementapp.services.repo.ProductNetworkRepository
import com.pack.projectmanagementapp.services.repo.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val productReposititory: ProductRepository
    private var networkRepo: ProductNetworkRepository
    private val _allProducts = MutableLiveData<List<Product>>()
    var allProducts: LiveData<List<Product>> = _allProducts

    companion object{
        private var fetchFlag = true;
    }

    init {
        val dao = ProductDatabase.getDatabase(application).getProductDao()
        networkRepo = ProductNetworkRepository()

        productReposititory = ProductRepository(dao, networkRepo)
        //getting the data from database
        allProducts = productReposititory.getAllProduct()
    }

    fun deleteAllProduct(allDeletedProductId:List<Int>) = viewModelScope.launch(Dispatchers.IO) {
        for (id in allDeletedProductId) {
            productReposititory.deleteById(id)
        }
    }



    fun fetchDataFromApi() {
        if(fetchFlag) {
            fetchFlag= false
            productReposititory.fetchProductDetails { productList ->
                viewModelScope.launch {
                    productList?.let { productReposititory.insert(it) }
                }
            }
        }
    }


}