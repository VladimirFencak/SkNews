package com.example.sknews.di

import android.content.Context
import com.example.sknews.feature_news.data.remote.NewsApi
import com.example.sknews.feature_news.data.repository.NewsRepositoryImp
import com.example.sknews.feature_news.domain.repository.NewsRepository
import com.example.sknews.feature_news.domain.use_case.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsRepository(api: NewsApi): NewsRepository{
        return NewsRepositoryImp(api)
    }

    @Singleton
    @Provides
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org")
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetNewsUseCase(repository: NewsRepository):GetNewsUseCase{
        return GetNewsUseCase(repository)
    }
}