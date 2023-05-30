package com.sancho.kotlin_retrofit_fakeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sancho.kotlin_retrofit_fakeapi.model.response.ProductModelItem
import com.sancho.kotlin_retrofit_fakeapi.retrofit.RetrofitInstance.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var textView: TextView
    lateinit var productAdapter: ProductAdapter
    lateinit var progressBar: ProgressBar
    var arrayList = ArrayList<ProductModelItem>()
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        progressBar = findViewById(R.id.progressbar1)
        swipeRefresh = findViewById(R.id.swiperefresh)

        swipeRefresh.setOnRefreshListener {
            Log.i("sancho", "onRefresh called from SwipeRefreshLayout")

        }


    }




    fun getAllProducts(){
        val call: Call<ArrayList<ProductModelItem>> = api.getAllproducts()
        progressBar.visibility = View.VISIBLE
        call.enqueue(object : Callback<ArrayList<ProductModelItem>> {
            override fun onResponse(
                call: Call<ArrayList<ProductModelItem>>,
                response: Response<ArrayList<ProductModelItem>>
            ) {

                if (response.isSuccessful) {
                    progressBar.visibility = View.INVISIBLE
                    arrayList = response.body()!!
                    Log.d("sancho", "onResponse: ${response.body()?.get(0)?.title}")
                    recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                    productAdapter = ProductAdapter(this@MainActivity, arrayList)
                    recyclerView.adapter = productAdapter

                    swipeRefresh.isRefreshing = false
                } else {
                    progressBar.visibility = View.INVISIBLE
                    swipeRefresh.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<ArrayList<ProductModelItem>>, t: Throwable) {

            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            // Check if user triggered a refresh:
            R.id.menu_refresh -> {
                Log.i("sancho", "Refresh menu item selected")

                // Signal SwipeRefreshLayout to start the progress indicator
                swipeRefresh.isRefreshing = true

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.

                return true
            }
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item)
    }
}