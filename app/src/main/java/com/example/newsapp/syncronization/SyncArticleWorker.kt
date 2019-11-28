package com.example.newsapp.syncronization

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newsapp.database.AppDataBase
import com.example.newsapp.injections.modules.RetrofitModule
import com.example.newsapp.models.Categories
import com.example.newsapp.rest.services.NewsServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit


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
            Log.d(TAG,"success")
            Result.success()

        } catch (error: Throwable) {
            Log.d(TAG,"error : ${error.message}")
            Result.failure()
        }
    }


    companion object {
        const val TAG = "SyncArticleWorker"
    }
}