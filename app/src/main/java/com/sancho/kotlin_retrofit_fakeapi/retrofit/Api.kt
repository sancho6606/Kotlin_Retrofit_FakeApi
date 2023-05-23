package com.sancho.kotlin_retrofit_fakeapi.retrofit

import com.sancho.kotlin_retrofit_fakeapi.model.ProductModelItem
import retrofit2.http.GET
import retrofit2.Call

interface Api {

    //https://fakestoreapi.com/products

    @GET("products")
    fun getAllproducts(): Call<ArrayList<ProductModelItem>>

}