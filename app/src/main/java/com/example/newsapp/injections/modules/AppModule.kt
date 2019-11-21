package com.example.newsapp.injections.modules

import android.content.Context
import androidx.work.*
import com.example.newsapp.AndroidApp
import com.example.newsapp.syncronization.SyncArticleWorker
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule constructor(val app: AndroidApp) {

    @Singleton
    @Provides
    fun provideApp(): AndroidApp = app

    @Provides
    @com.example.newsapp.injections.AppContext
    fun provideContext(): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideSyncJob(@com.example.newsapp.injections.AppContext context: Context): PeriodicWorkRequest {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()

        val syncWork = PeriodicWorkRequestBuilder<SyncArticleWorker>(30, TimeUnit.MINUTES)
            .setInitialDelay(5,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(syncWork)

        return syncWork
    }

}