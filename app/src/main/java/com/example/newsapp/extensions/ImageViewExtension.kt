package com.example.newsapp.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.newsapp.R
import com.example.newsapp.injections.modules.GlideApp





fun ImageView.loadNetworkImage(progressBar: View? = null, url: String) {

    progressBar?.visibility = View.VISIBLE

    GlideApp.with(this).load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().timeout(60 * 1000))
        .error(R.drawable.logo)
        .placeholder(R.drawable.logo)
        .addListener(object : RequestListener<Drawable> {

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }
        })
        .into(this)
    
}