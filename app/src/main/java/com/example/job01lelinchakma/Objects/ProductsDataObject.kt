package com.example.job01lelinchakma.Objects

import com.example.job01lelinchakma.Interface.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductsDataObject {
    private const val BASE_URL = "https://api.escuelajs.co/api/v1/"
    val api: ProductApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }
}