package com.example.repaitshopapplication.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repaitshopapplication.R
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.data.ProductDate
import com.example.repaitshopapplication.data.ProductTime
import com.example.repaitshopapplication.databinding.FragmentProductsBinding
import com.example.repaitshopapplication.ui.product.adapter.ProductsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import saman.zamani.persiandate.PersianDate

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private val viewModel: ProductsViewModel by viewModel()
    private lateinit var binding: FragmentProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false)
        viewModel.getProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        val rv = binding.productsRV
        rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            rv.adapter = ProductsAdapter(it)
        }
    }
}
