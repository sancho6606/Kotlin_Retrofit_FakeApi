package com.sancho.kotlin_retrofit_fakeapi

import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import com.sancho.kotlin_retrofit_fakeapi.databinding.ActivityMain4Binding
import com.sancho.kotlin_retrofit_fakeapi.model.request.ProductReq
import com.sancho.kotlin_retrofit_fakeapi.model.response.ProductRes
import com.sancho.kotlin_retrofit_fakeapi.retrofit.RetrofitInstance
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonaddproduct.setOnClickListener {
                addproduct(
                    title = producttitle.text.toString(),
                    price = productprice.text.toString().toDouble(),
                    description = productdescription.text.toString(),
                    image = "",
                    category = productcategory.text.toString()
                )
            }
        }

    }
    fun addproduct(
        title :String,
        price :Double,
        description :String,
        image :String,
        category :String
    ){
        val productReq=ProductReq(
            title = title,
            price = price,
            description = description,
            image = image,
            category = category
        )
        val call:retrofit2.Call<ProductRes> = RetrofitInstance.api.addproduct(productReq)
        call.enqueue(object :retrofit2.Callback<ProductRes>{
            override fun onResponse(call: retrofit2.Call<ProductRes>, response: Response<ProductRes>) {
                if (response.isSuccessful){
                    val productRes=response.body()!!
                    Log.d("sancho","onResponse: ${productRes.id}")
                    binding.textviewadd.text=productRes.id.toString()
                }
            }

            override fun onFailure(call: retrofit2.Call<ProductRes>, t: Throwable) {

            }
        })
    }
}