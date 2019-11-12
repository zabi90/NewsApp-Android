package de.starkling.newsapp.injections.modules

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory


/**
 * Created by Zohaib Akram on 2019-08-19
 * Copyright © 2019 Starkling. All rights reserved.
 */
@GlideModule
class MyAppGlideModule : AppGlideModule(){
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        val memoryCacheSizeBytes = 1024 * 1024 * 25 // 20mb

        val diskCacheSizeBytes = 1024 * 1024 * 100 // 100 MB


        val calculator = MemorySizeCalculator.Builder(context)
            .setBitmapPoolScreens(3f)
            .build()

        builder.setBitmapPool( LruBitmapPool(calculator.bitmapPoolSize.toLong()))
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
        builder.setLogLevel(Log.DEBUG)
    }
}