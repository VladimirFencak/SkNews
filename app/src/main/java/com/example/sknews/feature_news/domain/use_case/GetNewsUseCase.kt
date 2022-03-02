package com.example.sknews.feature_news.domain.use_case

import com.example.sknews.feature_news.data.remote.responses.News
import com.example.sknews.feature_news.domain.repository.NewsRepository
import com.example.sknews.feature_news.util.Resource
import java.lang.Exception
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): Resource<News>{
        val response =try {
            repository.getNews()
        } catch (e:Exception) {
            return Resource.Error(e.message ?:"Unexpected error")
        }
        return Resource.Success(response)
    }
}