package com.example.newsappmvvm.models

data class NewsResponseApi(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)