package com.example.sknews.feature_news.data.remote

import com.example.sknews.feature_news.data.remote.responses.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    //TODO use your api key
    @GET("/v2/top-headlines?country=sk&apiKey=yourApiKey")
    suspend fun getNews(): News
}