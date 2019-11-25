package com.example.newsapp.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.newsapp.base.BaseRepository
import com.example.newsapp.database.AppDataBase
import com.example.newsapp.models.Article
import com.example.newsapp.rest.services.NewsServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsServices: NewsServices,
    private val appDataBase: AppDataBase
) : BaseRepository() {

    private var job: Job? = null

    val newsHeadlineError by lazy {
        MutableLiveData<String?>()
    }

    val newsLoadingStatus by lazy {
        MutableLiveData<Boolean>()
    }
    /**
     * Read articles from database
     */
    fun getArticleFromDatabase(category: String): DataSource.Factory<Int, Article> {
        newsHeadlineError.postValue(null)
        newsLoadingStatus.postValue(false)
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


        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            Log.v(TAG, "onZeroItemsLoaded")

                job?.cancel()

                job = coroutineScope.launch {
                    try {
                        newsLoadingStatus.postValue(true)
                        val response = newsServices.getNewsByCategory(category, pageSize = 100)
                        insertArticleIntoDatabase(category, response.articles)
                        newsLoadingStatus.postValue(false)
                    } catch (exception: Exception) {
                        newsLoadingStatus.postValue(false)
                        newsHeadlineError.postValue(onHandleError(exception))
                    }
                }
        }

        override fun onItemAtEndLoaded(itemAtEnd: Article) {
            super.onItemAtEndLoaded(itemAtEnd)
            Log.v(TAG, "onItemAtEndLoaded")
        }

    }

    companion object {
        const val TAG = "ArticleBoundaryCallback"
    }
}


