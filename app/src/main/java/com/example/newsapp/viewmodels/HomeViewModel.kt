package com.example.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.models.Article
import com.example.newsapp.models.Categories
import com.example.newsapp.repositories.NewsRepository
import com.example.newsapp.rest.ApiResponseResource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

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