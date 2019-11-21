package com.example.newsapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.base.BaseRecyclerAdapter
import com.example.newsapp.extensions.loadNetworkImage
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.item_headline.view.*

class HeadlineAdapter(context: Context) : BaseRecyclerAdapter<Article, HeadlineAdapter.HeadlineViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HeadlineViewHolder {
        return HeadlineViewHolder(parent.inflate(R.layout.item_headline))
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    inner class HeadlineViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(article: Article){

            itemView.titleTextView.text = article.title
            itemView.publishDateTextView.text = article.publishedAt
            itemView.sourceTextView.text = article.source?.name

            article.urlToImage?.let {
                itemView.imageView.loadNetworkImage(itemView.progressBar,it)
            }

            itemView.setOnClickListener {
                listener?.onItemSelected(article,adapterPosition,itemView.imageView)
            }
        }
    }
}