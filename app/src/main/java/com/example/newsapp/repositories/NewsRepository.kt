package com.example.newsapp.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.injections.AppContext


import androidx.paging.DataSource
import androidx.paging.PagedList

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

import com.example.newsapp.base.BaseRepository
import com.example.newsapp.database.AppDataBase
import com.example.newsapp.models.Article
import com.example.newsapp.rest.services.NewsServices



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


