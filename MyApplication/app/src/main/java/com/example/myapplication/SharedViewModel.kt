package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.data.Product
import com.example.myapplication.data.ProductRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    val selectedProduct: MutableLiveData<Product> = MutableLiveData()
    val quantity: LiveData<Int> = _quantity
    private var productRepository: ProductRepository = ProductRepository()


//    val products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = liveData {
        val data = productRepository.getProducts()
        emit(data)
    }


//    init{
//        //val data = productRepository.getTextFromResource(app, R.raw.olive_oils_data)
////        val data = productRepository.getTextFromAsset(app, "olive_oils_data.json")
////        Log.i("two_trees_oil", data)
//        val data = productRepository.getProducts(app, "olive_oils_data.json")
//        data?.let {
//            products.value = it
//        }
////        data?.forEach{
////            Log.i("two_trees_oil", "Product: ${it.name}")
////        }
//    }

    fun increaseQuantity() {
        _quantity.value = _quantity.value!! + 1
    }

    fun decreaseQuantity() {
        if (_quantity.value!! > 0) {
            _quantity.value = _quantity.value!! - 1
        }

    }
}