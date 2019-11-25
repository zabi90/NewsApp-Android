package com.example.newsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.models.Article
import com.example.newsapp.models.Categories
import com.example.newsapp.repositories.NewsRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    BaseViewModel() {

    var currentCategory = Categories.GENERAL.getValue()


    private var config : PagedList.Config = PagedList.Config.Builder()
       .setPageSize(10)
       .setEnablePlaceholders(false)
       .build()

    init {
        initializedPagedListBuilder(config).build()
    }

    val newsHeadlineError = Transformations.map(newsRepository.newsHeadlineError) {
        return@map it
    }
    val newsLoadingStatus = Transformations.map(newsRepository.newsLoadingStatus) {
        return@map it
    }


    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Article> {


        return LivePagedListBuilder(
            newsRepository.getArticleFromDatabase(currentCategory),
            config).setBoundaryCallback(newsRepository.ArticleBoundaryCallback(viewModelScope,currentCategory))
    }

    fun getHeadline(): LiveData<PagedList<Article>> {
       return initializedPagedListBuilder(config).build()
    }
}