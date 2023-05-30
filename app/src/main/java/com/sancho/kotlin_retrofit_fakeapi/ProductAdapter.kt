package com.sancho.kotlin_retrofit_fakeapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.sancho.kotlin_retrofit_fakeapi.model.response.ProductModelItem

class ProductAdapter constructor(
    val context: Context,
    val arrayList: ArrayList<ProductModelItem>
):RecyclerView.Adapter<ProductAdapter.PostViewHolder>(){

    class PostViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val textView:TextView=itemview.findViewById(R.id.textviewresp)
        val imageView:ImageView=itemview.findViewById(R.id.imageviewresp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false)
        return PostViewHolder(view)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textView.text="${arrayList.get(position).title }"
        Glide.with(context).load(arrayList.get(position).image).into(holder.imageView)

        holder.imageView.setOnClickListener {
            val intent=Intent(context,MainActivity2::class.java)
            intent.apply {
                putExtra("title",arrayList.get(position).title)
                putExtra("description",arrayList.get(position).description)
                putExtra("price",arrayList.get(position).price)
                putExtra("image",arrayList.get(position).image)
                putExtra("rating",arrayList.get(position).rating.rate)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =arrayList.size


}