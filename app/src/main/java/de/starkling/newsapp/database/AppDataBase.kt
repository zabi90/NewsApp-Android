package de.starkling.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.starkling.newsapp.models.Article

@Database(entities = [Article::class], version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun articleDao():ArticleDao
}