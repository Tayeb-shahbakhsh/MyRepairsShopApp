package com.example.repaitshopapplication.ui.products.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.databinding.ProductItemBinding
import com.example.repaitshopapplication.utils.Status

class ProductsAdapter(private var data: List<Product>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(adapterPosition)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.product = data[position]
    }

    override fun getItemCount(): Int = data.size
}