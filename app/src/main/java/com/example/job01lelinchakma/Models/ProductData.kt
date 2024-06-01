package com.example.job01lelinchakma.Models

import java.io.Serializable

data class ProductData(
    val productID:Int,
    val productTitle:String,
    val productPrice:Int,
    val productDescription:String,
    val productImage:String,
    val productCategory:String,
): Serializable