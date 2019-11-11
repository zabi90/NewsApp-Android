package de.starkling.newsapp.rest.services

import de.starkling.newsapp.rest.response.DataResponse
import de.starkling.newsapp_android.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Zohaib Akram on 2019-11-11
 * Copyright © 2019 Starkling. All rights reserved.
 */
interface NewsServices {

    @GET("top-headlines?apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getNewsByCategory(@Query("category") category:String):Response<DataResponse>
}