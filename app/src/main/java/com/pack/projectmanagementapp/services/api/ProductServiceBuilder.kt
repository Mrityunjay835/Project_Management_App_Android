package com.pack.projectmanagementapp.services.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductServiceBuilder {

    private const val BASE_URL = "https://dummyjson.com/"

    //    create retrofit builder
    private val builder =
        Retrofit.Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create())

    // create retrofit instance
    private val retrofit = builder.build()

    //    This is use to create an anonymous inner class object that implement ProductDetailsService
    fun <T> builderService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }


}