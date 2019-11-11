package de.starkling.newsapp.injections.modules

import dagger.Module
import dagger.Provides
import de.starkling.newsapp.rest.services.NewsServices
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Zohaib Akram on 2019-11-11
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Module
class NetworkModule {

    @Provides
    fun getNewsService(@Named(RetrofitModule.PUBLIC_CLIENT) retrofit: Retrofit): NewsServices {
       return retrofit.create(NewsServices::class.java)
    }

}