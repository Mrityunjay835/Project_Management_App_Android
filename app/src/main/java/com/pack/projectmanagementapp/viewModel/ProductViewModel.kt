package com.pack.projectmanagementapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.services.local.ProductDatabase
import com.pack.projectmanagementapp.services.repo.ProductNetworkRepository
import com.pack.projectmanagementapp.services.repo.ProductRepository

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productReposititory: ProductRepository
    private var networkRepo: ProductNetworkRepository
    private val _products = MutableLiveData<Product>()
    var products: LiveData<Product> = _products

    init {
        val dao = ProductDatabase.getDatabase(application).getProductDao()
        networkRepo = ProductNetworkRepository()

        productReposititory = ProductRepository(dao, networkRepo)
        products = productReposititory.getProductById(1)
    }

}