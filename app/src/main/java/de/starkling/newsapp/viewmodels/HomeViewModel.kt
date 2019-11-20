package de.starkling.newsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import de.starkling.newsapp.base.BaseViewModel
import de.starkling.newsapp.models.Article
import de.starkling.newsapp.models.Categories
import de.starkling.newsapp.repositories.NewsRepository
import javax.inject.Inject

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    BaseViewModel() {

    var currentCategory = Categories.GENERAL.getValue()


    private var config : PagedList.Config = PagedList.Config.Builder()
       .setPageSize(10)
       .setEnablePlaceholders(true)
       .build()

    init {
        initializedPagedListBuilder(config).build()
    }

    val newsHeadlineError = Transformations.map(newsRepository.newsHeadlineError) {
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