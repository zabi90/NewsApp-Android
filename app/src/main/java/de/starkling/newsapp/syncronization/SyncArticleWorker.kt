package de.starkling.newsapp.syncronization

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import de.starkling.newsapp.database.AppDataBase
import de.starkling.newsapp.injections.modules.RetrofitModule
import de.starkling.newsapp.models.Categories
import de.starkling.newsapp.rest.services.NewsServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit


/**
 * Created by Zohaib Akram on 2019-11-13
 * Copyright © 2019 Starkling. All rights reserved.
 */
class SyncArticleWorker(val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {


    private val database: AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java, AppDataBase.DATABASE_NAME
    ).build()


    private val retrofit: Retrofit = RetrofitModule().getAuthRetrofitClient()


    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {

            val newsServices = retrofit.create(NewsServices::class.java)

            for (category in Categories.values()) {


                val response = newsServices.getNewsByCategory(category.getValue(),pageSize = 100)

                Log.v(
                    TAG,
                    "response : category : ${category.getValue()} totalResult: ${response.totalResults}"
                )

                val articles = response.articles


                val mapArticles = articles.map {
                    it.category = category.getValue()
                    return@map it
                }

                val result = database.articleDao().insertArticles(category.getValue(), mapArticles)


                Log.v(
                    TAG,
                    "database insertion : category : ${category.getValue()} row added: ${result.size}"
                )
            }
            Result.success()

        } catch (error: Throwable) {
            Result.retry()
        }
    }


    companion object {
        const val TAG = "SyncArticleWorker"
    }
}