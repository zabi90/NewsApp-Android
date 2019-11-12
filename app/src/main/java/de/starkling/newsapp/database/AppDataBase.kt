package de.starkling.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.starkling.newsapp.models.Category

@Database(entities = [Category::class], version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun categoryDao():CategoryDao
}