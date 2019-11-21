package com.example.newsapp.rest.response

import com.example.newsapp.models.Article


data class DataResponse(
    var status: String,
    var totalResults: Int,
    var articles : List<Article>
)