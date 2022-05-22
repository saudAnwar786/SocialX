package com.example.socialx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter : NewsAdapter
    private lateinit var newsArray : ArrayList<NewsData>
    private lateinit var tempnewsArray : ArrayList<NewsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsAdapter()
        recyclerView.adapter = mAdapter



    }
    private fun fetchData(){

        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=d43e770b9b9a41eabf962cd7e81c9c13"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,url,
            null, {
                val newsJsonArray = it.getJSONArray("articles")
                 newsArray = ArrayList<NewsData>()
                for( i in 0 until newsJsonArray.length()){
                    val newsJSONObject = newsJsonArray.getJSONObject(i)
                    val news = NewsData( newsJSONObject.getString("publishedAt"),
                        newsJSONObject.getString("author",),newsJSONObject.getString("title"),
                        newsJSONObject.getString("description"),newsJSONObject.getString("urlToImage")
                    )

                    newsArray.add(news)



                }
                mAdapter.updateNews(newsArray)
            },
            {

                Toast.makeText(this,"Error ! Not found",Toast.LENGTH_SHORT).show()
            },
        ) {
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }
        }


        MySingelaton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

}