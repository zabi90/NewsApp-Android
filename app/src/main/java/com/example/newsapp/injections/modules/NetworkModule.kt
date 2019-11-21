package com.example.newsapp.injections.modules

import com.example.newsapp.rest.services.NewsServices
import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun getNewsService(@Named(RetrofitModule.AUTH_CLIENT) retrofit: Retrofit): NewsServices {
       return retrofit.create(NewsServices::class.java)
    }

}