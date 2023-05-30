package com.sancho.kotlin_retrofit_fakeapi.retrofit

import com.sancho.kotlin_retrofit_fakeapi.model.request.ProductReq
import com.sancho.kotlin_retrofit_fakeapi.model.response.ProductModelItem
import com.sancho.kotlin_retrofit_fakeapi.model.response.ProductRes
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    //https://fakestoreapi.com/products

    @GET("products")
    fun getAllproducts(): Call<ArrayList<ProductModelItem>>

    //https://fakestoreapi.com/products/1
    @GET("products/{id}")
    fun getproductwithpid(@Path("id") id:Int):Call<ProductModelItem>

    //https://fakestoreapi.com/products?limit=5
    @GET("products")
    fun getproductsLimit(@Query("limit")limit:Int):Call<ArrayList<ProductModelItem>>

    //https://fakestoreapi.com/products
    @POST("products")
    fun addproduct(@Body productReq:ProductReq):Call<ProductRes>

}