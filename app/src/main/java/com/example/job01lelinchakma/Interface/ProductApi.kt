package com.example.job01lelinchakma.Interface

import com.example.job01lelinchakma.Models.ProductDataItem
import retrofit2.Response
import retrofit2.http.GET
interface ProductApi {
    @GET("products")
    suspend fun getProductData(): Response<List<ProductDataItem>>
}
