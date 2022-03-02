package com.example.sknews.feature_news.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.sknews.feature_news.data.remote.responses.Article
import com.example.sknews.feature_news.data.remote.responses.Source
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
){
    if(viewModel.isLoading.value){
        Text(text = "Loading")
    }
    if(viewModel.loadingError.value.isNotEmpty()){
        TryAgain(error = viewModel.loadingError.value) {
            viewModel.loadNews()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(viewModel.articlesList.value){ article->
            Text(text = article.title)
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
    color: Color = Color.Gray
){
    Box(modifier = modifier.background(color)
    ) {
        Image(painter = rememberImagePainter(
                request = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .build())
            , contentDescription = article.description
            , modifier = Modifier.clip(RoundedCornerShape(10.dp)))
    }
}

@Composable
fun TryAgain(
    error: String,
    onTryAgain: () -> Unit
){
    Column() {
        Text(text = error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = {onTryAgain()},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "TryAgain")
        }
    }
}

