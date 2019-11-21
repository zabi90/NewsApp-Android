package com.example.newsapp.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.base.BaseRepository
import com.example.newsapp.database.AppDataBase
import com.example.newsapp.models.Article
import com.example.newsapp.rest.services.NewsServices


class NewsRepository constructor(
    @com.example.newsapp.injections.AppContext context:Context,
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