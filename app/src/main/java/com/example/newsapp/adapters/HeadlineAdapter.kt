package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.base.OnItemSelectListener
import com.example.newsapp.extensions.loadNetworkImage
import com.example.newsapp.models.Article


import kotlinx.android.synthetic.main.item_headline.view.*

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class HeadlineAdapter() : PagedListAdapter<Article, HeadlineAdapter.HeadlineViewHolder>(DIFF_CALLBACK) {

    var listener: OnItemSelectListener<Article>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HeadlineViewHolder {
        return HeadlineViewHolder(parent.inflate(R.layout.item_headline))
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {


        holder.bindItem(getItem(position))
    }

    inner class HeadlineViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(article: Article?){

            article?.let {

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

    fun ViewGroup.inflate(layoutResId: Int): View {
        return LayoutInflater.from(context).inflate(layoutResId, this, false)
    }


    fun addListener(listener: OnItemSelectListener<Article>) {
        this.listener = listener
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Article>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldArticle: Article,
                                         newArticle: Article) = oldArticle.title == newArticle.title

            override fun areContentsTheSame(oldArticle: Article,
                                            newArticle: Article) = oldArticle == newArticle

        }
    }
}