package de.starkling.newsapp.injections.modules

import dagger.Module
import dagger.Provides
import de.starkling.newsapp.repositories.NewsRepository
import de.starkling.newsapp.rest.services.NewsServices
import javax.inject.Singleton

/**
 * Created by Zohaib Akram on 2019-11-12
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getNewsRepository(newsServices: NewsServices): NewsRepository {
        return NewsRepository(newsServices)
    }
}