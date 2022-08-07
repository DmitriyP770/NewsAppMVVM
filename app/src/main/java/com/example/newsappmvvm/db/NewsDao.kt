package com.example.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsappmvvm.models.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM articles_table")
    fun getArticlesFromDb():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}