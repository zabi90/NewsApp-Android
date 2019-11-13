package de.starkling.newsapp.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import de.starkling.newsapp.base.BaseRepository
import de.starkling.newsapp.database.AppDataBase
import de.starkling.newsapp.injections.AppContext
import de.starkling.newsapp.models.Article
import de.starkling.newsapp.rest.services.NewsServices

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class NewsRepository constructor(
    @AppContext context:Context,
    private val newsServices: NewsServices,
    private val appDataBase: AppDataBase
) : BaseRepository() {

    val newsHeadlineError by lazy {
        MutableLiveData<String>()
    }


    suspend fun getHeadlines(category: String): List<Article>? {
        try {

            val localArticles = getArticleFromDatabase(category)

            return if (localArticles.isNotEmpty()) {
                localArticles
            } else {
                //send network request
                val dataResponse = newsServices.getNewsByCategory(category)
                val articles = dataResponse.articles
                insertArticleIntoDatabase(category,articles)
                articles
            }

        } catch (exception: Exception) {
            newsHeadlineError.postValue(onHandleError(exception))
        }
        return null
    }

    /**
     * Read articles from database
     */
    private suspend fun getArticleFromDatabase(category: String): List<Article> {
        return appDataBase.articleDao().getArticles(category)
    }

    /**
     * Insert article into local database
     */
    private suspend fun insertArticleIntoDatabase(category: String,articles: List<Article>): Array<Long> {
        //map with categories
        val mapArticles = articles.map {
            it.category = category
            return@map it
        }
        return appDataBase.articleDao().insertArticles(category,mapArticles)
    }
}