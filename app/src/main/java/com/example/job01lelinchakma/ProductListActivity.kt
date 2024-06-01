package com.example.job01lelinchakma

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.job01lelinchakma.Adapter.ProductShowAdapter
import com.example.job01lelinchakma.Models.ProductData
import com.example.job01lelinchakma.Models.ProductDataItem
import com.example.job01lelinchakma.Objects.ProductsDataObject
import com.example.job01lelinchakma.Utils.LoadingClass
import com.example.job01lelinchakma.databinding.ActivityProductListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }
    private lateinit var progressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getProduct()
        progressDialog = LoadingClass().setProgressDialog(this)
        showProgressDialog()
    }


    private fun getProduct(){
        binding.productDataList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        lifecycleScope.launch {
            val response = ProductsDataObject.api.getProductData()
            if (response.isSuccessful) {
                response.body()?.let { productData ->
                    withContext(Dispatchers.Main) {
                        if (productData.isNotEmpty()) {
                            hideProgressDialog()
                            val productList = productData.map {
                                ProductData(
                                    productID = it.id,
                                    productTitle = it.title,
                                    productPrice = it.price,
                                    productImage = it.images[0],
                                    productDescription = it.description,
                                    productCategory = it.category.name.toString()
                                )
                            }
                            val recyclerAdapter = ProductShowAdapter(productList) { selectedProduct ->
                                Toast.makeText(this@ProductListActivity, selectedProduct.productTitle, Toast.LENGTH_SHORT).show()
                            }
                            binding.productDataList.adapter = recyclerAdapter
                        } else {
                            showEmptyState()
                        }
                    }
                }
            } else {
                showErrorState()
            }
        }
    }

    private fun showProgressDialog() {
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    private fun showEmptyState() {
        // Implement this method to show an empty state in your UI
    }

    private fun showErrorState() {
        // Implement this method to show an error state in your UI
    }
}