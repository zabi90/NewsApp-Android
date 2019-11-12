package de.starkling.newsapp.injections.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import de.starkling.newsapp.database.AppDataBase
import de.starkling.newsapp.injections.AppContext
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun getDatabase(@AppContext context:Context): AppDataBase {
        return  Room.databaseBuilder(
            context,
            AppDataBase::class.java, "news_app"
        ).build()
    }
}