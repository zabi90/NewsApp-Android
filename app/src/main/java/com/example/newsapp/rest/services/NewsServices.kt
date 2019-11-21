package com.example.newsapp.rest.services

import com.example.newsapp.rest.response.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsServices {

    @GET("top-headlines?country=us")
    suspend fun getNewsByCategory(
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page:Int = 1,
        @Query("country") country: String = "us"
    ): DataResponse
}