package de.starkling.newsapp.rest.services

import de.starkling.newsapp.rest.response.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Zohaib Akram on 2019-11-11
 * Copyright © 2019 Starkling. All rights reserved.
 */
interface NewsServices {

    @GET("top-headlines")
    suspend fun getNewsByCategory(@Query("category") category:String):Response<DataResponse>
}