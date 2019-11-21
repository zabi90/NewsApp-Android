package com.example.newsapp.injections.modules

import android.content.Context
import androidx.room.Room
import com.example.newsapp.database.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun getDatabase(@com.example.newsapp.injections.AppContext context:Context): AppDataBase {
        return  Room.databaseBuilder(
            context,
            AppDataBase::class.java, AppDataBase.DATABASE_NAME
        ).build()
    }
}