package com.example.job01lelinchakma.Models

import com.google.gson.annotations.SerializedName
data class ProductDataItem(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Int,
    @SerializedName("description") val description: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("category") val category: Category
)
