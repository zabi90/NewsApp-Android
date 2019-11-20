package de.starkling.newsapp.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import de.starkling.newsapp.base.BaseRepository
import de.starkling.newsapp.database.AppDataBase
import de.starkling.newsapp.injections.AppContext
import de.starkling.newsapp.models.Article
import de.starkling.newsapp.rest.services.NewsServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class NewsRepository constructor(
    @AppContext context: Context,
    private val newsServices: NewsServices,
    private val appDataBase: AppDataBase
) : BaseRepository() {

    private var job: Job? = null

    val newsHeadlineError by lazy {
        MutableLiveData<String>()
    }

    /**
     * Read articles from database
     */
    fun getArticleFromDatabase(category: String): DataSource.Factory<Int, Article> {
        return appDataBase.articleDao().getArticles(category)
    }

    /**
     * Insert article into local database
     */
    private suspend fun insertArticleIntoDatabase(
        category: String,
        articles: List<Article>
    ): Array<Long> {
        //map with categories
        val mapArticles = articles.map {
            it.category = category
            return@map it
        }
        return appDataBase.articleDao().insertArticles(category, mapArticles)
    }

    inner class ArticleBoundaryCallback(
        private val coroutineScope: CoroutineScope,
        private var category: String
    ) : PagedList.BoundaryCallback<Article>() {

        private var isLoading = false

        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            Log.v(TAG, "onZeroItemsLoaded")

                job?.cancel()

                job = coroutineScope.launch {
                    try {
                        val response = newsServices.getNewsByCategory(category, pageSize = 20, page = 1)
                        insertArticleIntoDatabase(category, response.articles)
                    } catch (exception: Exception) {
                        newsHeadlineError.postValue(onHandleError(exception))
                    }
                }

        }

        override fun onItemAtEndLoaded(itemAtEnd: Article) {
            super.onItemAtEndLoaded(itemAtEnd)
            Log.v(TAG, "onItemAtEndLoaded")

            if(isLoading){
                return
            }

            job = coroutineScope.launch {
                try {
                    Log.v(TAG, "onItemAtEndLoaded : launch")
                    isLoading = true
                    val response = newsServices.getNewsByCategory(category, pageSize = 20, page = 2)
                    insertArticleIntoDatabase(category, response.articles)
                    isLoading = false
                } catch (exception: Exception) {
                    isLoading = false
                    newsHeadlineError.postValue(onHandleError(exception))
                }
            }
        }

    }

    companion object {
        const val TAG = "ArticleBoundaryCallback"
    }
}


