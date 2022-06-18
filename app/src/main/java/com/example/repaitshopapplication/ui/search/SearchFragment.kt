package com.example.repaitshopapplication.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.repaitshopapplication.databinding.FragmentSearchBinding
import com.example.repaitshopapplication.ui.products.ProductsFragmentDirections
import com.example.repaitshopapplication.ui.products.ProductsViewModel
import com.example.repaitshopapplication.ui.products.adapter.ProductsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), ProductsAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewModel.getAllProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchBar()
        setupSearchRecyclerView()
    }

    private fun setupSearchRecyclerView() {
        binding.searchRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.filteredProductsLiveDate.observe(viewLifecycleOwner) {
            binding.searchRv.adapter = it?.let { products -> ProductsAdapter(products, this) }
        }
    }

    private fun setupSearchBar() {
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.findProductFromDataBase(s.toString())
            }
        })
    }

    override fun onItemClick(position: Int) {
        binding.searchEt.setText("")
        val product = viewModel.productLiveData.value?.get(position)
        val action =
            SearchFragmentDirections.actionSearchFragmentToProductFragment(position, product)
        findNavController().navigate(action)
    }

}