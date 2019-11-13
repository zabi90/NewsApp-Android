package de.starkling.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import de.starkling.newsapp.models.Article

@Dao
abstract class ArticleDao {

    @Insert
    abstract suspend fun insertAll(articles: List<Article>) : Array<Long>

    @Transaction
    open suspend fun insertArticles(category:String,articles: List<Article>): Array<Long> {
        delete(category)
        return insertAll(articles)
    }

    @Query("Select * from article where category = :category")
    abstract suspend fun getArticles(category:String):List<Article>

    @Query("Delete from article where category  = :category ")
    abstract suspend fun delete(category:String)
}