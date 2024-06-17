package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.Product
import com.example.myapplication.databinding.FragmentShopBinding

class FragmentShop : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel:SharedViewModel?=null


    private val onItemClick:(Product)->Unit={product->
        Log.i(LOG_TAG, "The selected product: $product")
        viewModel?.selectedProduct?.value = product
        findNavController().navigate(R.id.action_fragmentShop3_to_detailFragment)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        }

        viewModel?.products?.observe(viewLifecycleOwner) { products ->
            binding.productList.adapter = ProductAdapter(products, onItemClick)

        }

        val product = Product (
        name = "Item",
        imageFile = "image_file",
        description = "This is my description",
        size = 200,
        price = 12.35,
        )

        Log.i("two_trees_oil", "My product: $product")

//        binding.addQuantityButton.setOnClickListener { viewModel?.increaseQuantity() }
//        binding.removeQuantityButton.setOnClickListener { viewModel?.decreaseQuantity() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}