package de.starkling.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import de.starkling.newsapp.base.BaseViewModel
import de.starkling.newsapp.models.Article
import de.starkling.newsapp.models.Categories
import de.starkling.newsapp.repositories.NewsRepository
import de.starkling.newsapp.rest.ApiResponseResource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    BaseViewModel() {

    var currentCategory = Categories.GENERAL.getValue()

    val newsHeadlineResponse by lazy {
        MutableLiveData<ApiResponseResource<List<Article>?>>()
    }

    val newsHeadlineError = Transformations.map(newsRepository.newsHeadlineError) {
        return@map it
    }

    private var currentNewsHeadlineJob: Job? = null

    fun getHeadline() {

        if (newsHeadlineResponse.value?.data == null) {
            newsHeadlineResponse.value = ApiResponseResource.loading()

            currentNewsHeadlineJob?.cancel()

            currentNewsHeadlineJob = viewModelScope.launch {
                val articles = newsRepository.getHeadlines(currentCategory)
                newsHeadlineResponse.value = ApiResponseResource.success(articles)
            }
        }
    }
}