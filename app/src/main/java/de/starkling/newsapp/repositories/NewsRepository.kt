package de.starkling.newsapp.repositories

import androidx.lifecycle.MutableLiveData
import de.starkling.newsapp.base.BaseRepository
import de.starkling.newsapp.database.AppDataBase
import de.starkling.newsapp.rest.response.DataResponse
import de.starkling.newsapp.rest.services.NewsServices

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class NewsRepository constructor(private val newsServices: NewsServices,private val appDataBase: AppDataBase): BaseRepository() {

    val newsHeadlineError by lazy {
        MutableLiveData<String>()
    }

    suspend fun getHeadlines(category: String): DataResponse? {
        try {
            return newsServices.getNewsByCategory(category)
        } catch (exception: Exception) {
            newsHeadlineError.postValue(onHandleError(exception))
        }
        return null
    }
}