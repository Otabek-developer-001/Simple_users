package com.example.networkrecyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.networkrecyle.adapter.NewsAdapter
import com.example.networkrecyle.databinding.ActivityMainBinding
import com.example.networkrecyle.model.News

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiSimple = "https://reqres.in/api/users"
    private var newsList = arrayListOf<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val requestQueue:RequestQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.GET,apiSimple,null,{ res ->
            Log.d("Volley",res.getString("page"))
            val jsonArray = res.getJSONArray("data")
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val news = News(
                    jsonObject.getInt("id"),
                    jsonObject.getString("email"),
                    jsonObject.getString("first_name"),
                    jsonObject.getString("last_name"),
                    jsonObject.getString("avatar")
                )
                newsList.add(news)
        }
            binding.recycleTv.layoutManager = LinearLayoutManager(this)
            binding.recycleTv.adapter  = NewsAdapter(newsList)
        },{err ->
            Log.d("Volley error!!!",err.message.toString())
        })
        requestQueue.add(request)
    }
}