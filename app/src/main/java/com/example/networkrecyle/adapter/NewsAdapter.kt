package com.example.networkrecyle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkrecyle.databinding.ItemLayoutBinding
import com.example.networkrecyle.model.News

class NewsAdapter (private var newsList: ArrayList<News>):
    RecyclerView.Adapter<NewsAdapter.VH>() {

    private lateinit var context:Context

    inner class VH (var itemLayoutBinding: ItemLayoutBinding):
            RecyclerView.ViewHolder(itemLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        return VH(ItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val news = newsList[position]

        Glide.with(context).load(news.image).into(holder.itemLayoutBinding.imageView)

        val stringBuilder = StringBuilder()

        stringBuilder.append(news.fName).append(news.lName)
        holder.itemLayoutBinding.firstName.text = stringBuilder
        holder.itemLayoutBinding.email.text = news.emailId

    }
}