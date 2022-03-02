package com.example.sknews.feature_news.data.repository

import com.example.sknews.feature_news.data.remote.NewsApi
import com.example.sknews.feature_news.data.remote.responses.News
import com.example.sknews.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImp(
    private val api: NewsApi
): NewsRepository {
    override suspend fun getNews(): News {
        //return api.getNews(apiKey)
        return api.getNews()
    }
}