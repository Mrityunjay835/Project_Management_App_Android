package com.pack.projectmanagementapp.services.repo

import android.util.Log
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.entities.ProductsDetails
import com.pack.projectmanagementapp.services.api.ProductService
import com.pack.projectmanagementapp.services.api.ProductServiceBuilder
import retrofit2.Callback

class ProductNetworkRepository {

    private val apiService = ProductServiceBuilder.builderService(ProductService::class.java)

    fun fetchProductsDetails(callback: Callback<ProductsDetails>){
        val call  =apiService.getProductsDetails()
        call.enqueue(callback)
    }
}