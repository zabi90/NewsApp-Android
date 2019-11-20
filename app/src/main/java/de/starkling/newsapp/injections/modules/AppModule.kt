package de.starkling.newsapp.injections.modules

import android.content.Context
import androidx.work.*
import dagger.Module
import dagger.Provides
import de.starkling.newsapp.AndroidApp
import de.starkling.newsapp.injections.AppContext
import de.starkling.newsapp.syncronization.SyncArticleWorker
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule constructor(val app:AndroidApp) {

    @Singleton
    @Provides
    fun provideApp(): AndroidApp = app

    @Provides
    @AppContext
    fun provideContext(): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideSyncJob(@AppContext context: Context): PeriodicWorkRequest {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()

        val syncWork = PeriodicWorkRequestBuilder<SyncArticleWorker>(30, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(context)
        //workManager.enqueue(syncWork)

        return syncWork
    }

}