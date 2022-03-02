package com.example.sknews.feature_news.domain.repository

import com.example.sknews.feature_news.data.remote.responses.News

interface NewsRepository {

    suspend fun getNews(): News
}