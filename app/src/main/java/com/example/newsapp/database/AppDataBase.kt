package com.example.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.models.Article


@Database(entities = [Article::class], version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun articleDao():ArticleDao
    companion object{
        const val DATABASE_NAME = "news_app"
    }
}