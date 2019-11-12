package de.starkling.newsapp.injections.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import de.starkling.newsapp.AndroidApp
import de.starkling.newsapp.injections.AppContext
import javax.inject.Singleton

@Module
class AppModule constructor(val app:AndroidApp) {

    @Singleton
    @Provides
    fun provideApp(): AndroidApp = app

    @Provides
    @AppContext
    internal fun provideContext(): Context {
        return app.applicationContext
    }

}