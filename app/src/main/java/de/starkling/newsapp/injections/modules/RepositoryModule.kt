package de.starkling.newsapp.injections.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import de.starkling.newsapp.database.AppDataBase
import de.starkling.newsapp.injections.AppContext
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
    fun getNewsRepository(@AppContext context: Context, newsServices: NewsServices, dataBase: AppDataBase): NewsRepository {
        return NewsRepository(context,newsServices,dataBase)
    }
}