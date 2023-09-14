package com.pack.projectmanagementapp.services.api

import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.entities.ProductsDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductService {

//    @Headers(
//        "X-Firefox-Spdy: h2",
//        "User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0",
//        "Content-Type: application/json" // Make sure this line is formatted correctly
//    )
    @GET("products")
    fun getProductsDetails(): Call<ProductsDetails>
}