package com.example.job01lelinchakma.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.job01lelinchakma.Models.ProductData
import com.example.job01lelinchakma.databinding.ProductShowListBinding
import com.squareup.picasso.Picasso

class ProductShowAdapter (private val itemList: List<ProductData>, private val clickListener: (ProductData) -> Unit): RecyclerView.Adapter<ProductShowAdapter.InfiniteViewHolder>() {
    class InfiniteViewHolder(val binding: ProductShowListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productList: ProductData, clickListener: (ProductData) -> Unit) {
            binding.titleShow.text = productList.productTitle
            binding.categoryShow.text = productList.productCategory
            binding.priceShow.text = "$${productList.productPrice.toString()}"
            binding.descriptionShow.text = productList.productDescription
            Picasso.get().load(productList.productImage).into(binding.imageShow)
            itemView.setOnClickListener { clickListener(productList) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteViewHolder {
        val binding = ProductShowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfiniteViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: InfiniteViewHolder, position: Int) {
        holder.bind(itemList[position], clickListener)
    }
}