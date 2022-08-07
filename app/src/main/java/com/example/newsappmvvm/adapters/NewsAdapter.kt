package com.example.newsappmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappmvvm.R
import com.example.newsappmvvm.models.Article

class NewsAdapter (): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private val differCallback = object : DiffUtil.ItemCallback<Article> (){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
     val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val adapter = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article_preview, parent, false)
        return NewsViewHolder(adapter)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        //itemView - is from ViewHolder class
        holder.itemView.apply {
           Glide.with(this).load(item.urlToImage).into(holder.ivArticleImage)
            holder.tvTitle.text = item.title
            holder.tvDescription.text = item.description
            holder.tvPublishedAt.text = item.publishedAt
            setOnClickListener{
                onItemClickListener?.let {
                    it(item)
                }
            }


        }

    }

    private var onItemClickListener : ((Article) -> Unit)? = null
    private fun setOnItemClickListener (listener : (Article) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    inner class NewsViewHolder(item:View):RecyclerView.ViewHolder(item){
        val ivArticleImage : ImageView = item.findViewById(R.id.ivArticleImage)
        val tvTitle: TextView = item.findViewById(R.id.tvTitle)
        val tvDescription : TextView = item.findViewById(R.id.tvDescription)
        val tvPublishedAt : TextView = item.findViewById(R.id.tvPublishedAt)

    }
}
/*
     val adapterLayout = LayoutInflater.from(parent.context).
        inflate(R.layout.list_item,parent,false)
        return ItemViewHolder(adapterLayout)
 */