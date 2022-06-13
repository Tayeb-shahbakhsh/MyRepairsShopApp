package com.example.repaitshopapplication.ui.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repaitshopapplication.R
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.databinding.ProductItemBinding
import com.example.repaitshopapplication.ui.product.ProductsFragment

class ProductsAdapter(private var data: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.product = data[position]
    }

    override fun getItemCount(): Int = data.size
}