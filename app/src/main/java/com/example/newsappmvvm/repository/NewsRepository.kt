package com.example.newsappmvvm.repository

import com.example.newsappmvvm.api.RetrofitInstance
import com.example.newsappmvvm.db.ArticlesDataBase
import com.example.newsappmvvm.models.NewsResponseApi

class NewsRepository(
    val dataBase: ArticlesDataBase
) {

    suspend fun getNewsFromApi(countryCode: String, page:Int) = RetrofitInstance.api.getBreakingNews(
       countryCode,
       page
   )

    suspend fun searchNews(query:String, page: Int) = RetrofitInstance.api.getAllNews(query,page)

}