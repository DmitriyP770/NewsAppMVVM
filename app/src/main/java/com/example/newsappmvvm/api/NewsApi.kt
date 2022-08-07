package com.example.newsappmvvm.api

import com.example.newsappmvvm.BuildConfig
import com.example.newsappmvvm.models.NewsResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

val API_KEY = BuildConfig.API_KEY

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(

        @Query("country")
        countyCode:String = "us",

        @Query("page")
        page:Int = 1,

        @Query("apiKey")
        apiKey:String = API_KEY


    ) :Response<NewsResponseApi>


    @GET("v2/everything")
    suspend fun getAllNews(

        @Query("q")
        searchQuery:String,

        @Query("page")
        page:Int = 1,

        @Query("apiKey")
        apiKey:String = API_KEY,

        @Query("pageSize")
        pageSize:Int = 50,

    ):Response<NewsResponseApi>
}