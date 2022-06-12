package com.example.repaitshopapplication.ui.product.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.databinding.ProductItemBinding
import com.example.repaitshopapplication.ui.product.ProductsFragment

class ProductsAdapter(private var data: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        private var productItemBinding: ProductItemBinding? = null

        init {
            productItemBinding = ProductItemBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = data.size
}