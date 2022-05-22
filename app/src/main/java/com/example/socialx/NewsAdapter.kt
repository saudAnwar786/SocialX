package com.example.socialx

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter()
    : RecyclerView.Adapter<NewsViewHolder>() {
    private val items :  ArrayList<NewsData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item , parent,false)
        val viewHolder = NewsViewHolder(view)
        return viewHolder
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.descriptionNews.text = currentItem.description
        holder.time.text = currentItem.time
        holder.source.text =  currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl)
            .into(holder.image)


    }


    fun updateNews(updateNews: ArrayList<NewsData>){
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return items.size
    }
}



class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView ){
    val titleView : TextView = itemView.findViewById(R.id.tvNewsheading)
    val source : TextView = itemView.findViewById(R.id.tvnewsSource)
    val time : TextView = itemView.findViewById(R.id.timepassed)
    val image: ImageView = itemView.findViewById(R.id.imagenews)
    val descriptionNews : TextView = itemView.findViewById(R.id.textView18)

}