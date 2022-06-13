package com.example.repaitshopapplication.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.repaitshopapplication.R
import com.example.repaitshopapplication.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
    }

}