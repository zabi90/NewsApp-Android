package com.example.newsapp.database

import androidx.paging.DataSource
import androidx.room.*
import com.example.newsapp.models.Article


@Dao
abstract class ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(articles: List<Article>) : Array<Long>

    @Transaction
    open suspend fun insertArticles(category:String,articles: List<Article>): Array<Long> {
        delete(category)
        return insertAll(articles)
    }

    @Query("Select * from article where category = :category")
    abstract  fun getArticles(category:String): DataSource.Factory<Int, Article>

    @Query("Delete from article where category  = :category ")
    abstract suspend fun delete(category:String)
}