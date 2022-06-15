package com.example.repaitshopapplication.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.repaitshopapplication.R
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.databinding.DialogCreateProductBinding
import com.example.repaitshopapplication.databinding.FragmentProductBinding
import com.example.repaitshopapplication.databinding.FragmentProductsBinding
import com.example.repaitshopapplication.utils.Status
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {

    companion object {
        fun newInstance() = ProductFragment()
    }


    private lateinit var binding: FragmentProductBinding
    private val viewModel: ProductViewModel by viewModel()
    private val args :ProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        binding.product = args.product

        args.product?.apply {
            binding.productIv.setImageURI(imagePath.toUri())

            binding.removeBtn.setOnClickListener {
                viewModel.removeProduct(args.product!!)
                findNavController().popBackStack()
            }

            binding.goBackBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            problems.forEach { problemText ->
                var problemBadgeView = MaterialTextView(requireContext())
                problemBadgeView.setBackgroundColor(resources.getColor(R.color.warning, null))
                problemBadgeView.text = problemText
                binding.gridLayout.addView(problemBadgeView)
            }

        }
    }

    fun makeProblemText(view: DialogCreateProductBinding): String =
        "  ${view.problemCountNumberPicker.value} * ${view.problemAc.text}  "


}