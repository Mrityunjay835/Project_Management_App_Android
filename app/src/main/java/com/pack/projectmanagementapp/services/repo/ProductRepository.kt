package com.pack.projectmanagementapp.services.repo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.entities.ProductsDetails
import com.pack.projectmanagementapp.services.local.ProductDao
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductRepository(
    private val productDao: ProductDao,
    private val networkRepo: ProductNetworkRepository
) {

    fun getAllProduct(): LiveData<List<Product>> {
        return productDao.getAllProduct()
    }

    fun getProductById(id:Int):LiveData<Product>{
        return productDao.getProductById(id)
    }


    suspend fun insert(productList: ArrayList<Product>) {
        //Log.d("afterFetchDataFromApi","${productList[0]}")
        for (de in productList) {
            productDao.insertProduct(de)
        }
    }



    suspend fun update(dataEntity: Product) {
        productDao.updateProduct((dataEntity))
    }

    suspend fun delete(dataEntity: Product) {
        productDao.deleteProduct(dataEntity)
    }

    fun deleteById(id: Int){
        productDao.deleteProductById(id)
    }


    fun fetchProductDetails(productCallback: (ArrayList<Product>?) -> Unit) {
        networkRepo.fetchProductsDetails(object : Callback<ProductsDetails> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<ProductsDetails>,
                response: Response<ProductsDetails>
            ) {
                if (response.isSuccessful) {
                    val productsResponse = response.body()
                    val products = productsResponse?.products as ArrayList
                        if (products!=null){
                            productCallback(products)
                        }else{
                            productCallback(null)
                        }
                }
            }

            override fun onFailure(call: Call<ProductsDetails>, t: Throwable) {
                Log.e("HomeActivityViewModelTesting", "${t.message}")
                productCallback(null)
            }
        })
    }
}