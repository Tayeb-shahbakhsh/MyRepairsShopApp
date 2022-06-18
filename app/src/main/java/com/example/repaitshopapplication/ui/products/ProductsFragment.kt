package com.example.repaitshopapplication.ui.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repaitshopapplication.R
import com.example.repaitshopapplication.databinding.FragmentProductsBinding
import com.example.repaitshopapplication.ui.products.adapter.ProductsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment(), ProductsAdapter.OnItemClickListener {

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
        initProductsCount()
    }

    private fun initProductsCount() {
        val textView = binding.productsCountTv

        viewModel.productsLiveData.observe(viewLifecycleOwner){
            textView.text = it.size.toString()
        }
    }

    private fun setupAdapter() {
        val rv = binding.productsRV
        rv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            rv.adapter = ProductsAdapter(it, this)
        }
    }

    override fun onItemClick(position: Int, id: Int) {
        navigationToProductDetail(position)
    }

    private fun navigationToProductDetail(position: Int) {
        val product = viewModel.productsLiveData.value?.get(position)
        val action = ProductsFragmentDirections.actionProductsFragmentToProductFragment(position,product)
        findNavController().navigate(action)
    }
}
