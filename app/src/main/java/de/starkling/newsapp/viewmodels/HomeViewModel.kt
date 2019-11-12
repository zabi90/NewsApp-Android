package de.starkling.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.starkling.newsapp.base.BaseViewModel
import de.starkling.newsapp.models.Article
import de.starkling.newsapp.repositories.NewsRepository
import de.starkling.newsapp.rest.ApiResponseResource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) : BaseViewModel() {

    private var currentNewsHeadlineJob :Job? = null

    fun getHeadline(category: String = "general"): MutableLiveData<ApiResponseResource<List<Article>?>> {

        val response = MutableLiveData<ApiResponseResource<List<Article>?>>()
        response.value = ApiResponseResource.loading()

        currentNewsHeadlineJob?.cancel()

        currentNewsHeadlineJob = viewModelScope.launch {
            val dataResponse = newsRepository.getHeadlines(category)
            response.value = ApiResponseResource.success(dataResponse?.articles)
        }

        return response
    }
}