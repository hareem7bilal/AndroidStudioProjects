package com.example.myapplication.data
import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

const val BASE_ENDPOINT_URL = "https://2873199.youcanlearnit.net/"

class ProductRepository {

    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        Retrofit.Builder().baseUrl(BASE_ENDPOINT_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    private val productApi: ProductApi by lazy{
        retrofit.create(ProductApi::class.java)
    }

    fun getTextFromResource(context: Context, resourceId: Int):String{
        return context.resources.openRawResource(resourceId).bufferedReader().use {it.readText()}

    }

    fun getTextFromAsset(context: Context, fileName: String):String{
        return context.resources.assets.open(fileName).bufferedReader().use {it.readText()}

    }

    suspend fun getProducts(//context: Context, fileName: String
    ):List<Product>{
//        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//        val listType = Types.newParameterizedType(
//            List::class.java, Product::class.java
//        )
//
//        val adapter: JsonAdapter<List<Product>> = moshi.adapter(listType)
//        return adapter.fromJson(getTextFromAsset(context,fileName))
        val response = productApi.getProducts()
        return if (response.isSuccessful) response.body() ?: emptyList()
        else emptyList()

    }


}