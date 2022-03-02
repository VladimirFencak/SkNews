package com.example.sknews.feature_news.presentation.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sknews.feature_news.data.remote.responses.Article
import com.example.sknews.feature_news.domain.use_case.GetNewsUseCase
import com.example.sknews.feature_news.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel(){

    private var _articlesList = mutableStateOf<List<Article>>(listOf())
    var articlesList: State<List<Article>> = _articlesList

    private var _loadingError = mutableStateOf("")
    var loadingError: State<String> = _loadingError

    private var _isLoading = mutableStateOf(false)
    var isLoading: State<Boolean> = _isLoading

    init {
        loadNews()
    }

    fun loadNews(){
        viewModelScope.launch {
            _isLoading.value = true
            when(val news =getNewsUseCase()){
                is Resource.Success -> {
                    _articlesList.value = news.data?.articles ?: listOf()
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    _loadingError.value = news.message ?:"unexpected error"
                    _isLoading.value = false
                }
            }
        }
    }
}