package de.starkling.newsapp.repositories

import de.starkling.newsapp.base.BaseRepository
import de.starkling.newsapp.rest.response.DataResponse
import de.starkling.newsapp.rest.services.NewsServices

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class NewsRepository constructor(private val newsServices: NewsServices): BaseRepository() {

    suspend fun getHeadlines(category: String): DataResponse? {

        val response = newsServices.getNewsByCategory(category)

        try {
            if (response.isSuccessful) {
                return response.body()
            }else{
                response.errorBody()
            }

        } catch (exception: Exception) {
            onHandleError(exception)
        }
        return null
    }
}