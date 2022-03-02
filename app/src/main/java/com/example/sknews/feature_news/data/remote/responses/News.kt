package com.example.sknews.feature_news.data.remote.responses

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)