package de.starkling.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.starkling.newsapp.base.BaseRecyclerAdapter
import de.starkling.newsapp.extensions.loadNetworkImage
import de.starkling.newsapp.models.Article
import de.starkling.newsapp_android.R
import kotlinx.android.synthetic.main.item_headline.view.*

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class HeadlineAdapter(context: Context) : BaseRecyclerAdapter<Article, HeadlineAdapter.HeadlineViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
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