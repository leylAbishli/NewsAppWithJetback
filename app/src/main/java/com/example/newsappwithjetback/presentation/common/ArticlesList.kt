package com.example.newsappwithjetback.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsappwithjetback.domain.model.Article
import com.example.newsappwithjetback.presentation.Dimens.ExtraSmallPadding2
import com.example.newsappwithjetback.presentation.Dimens.MediumPadding1
import com.example.newsappwithjetback.presentation.home.components.ArticleCard
@Composable
fun  ArticlesList(
    modifier: Modifier=Modifier,
    article: List<Article>,
    onClick: (Article) -> Unit
) {
    if(article.isEmpty()){
        EmptyScreen()
    }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = article.size) {
                val article=article[it]
                    ArticleCard(article = article, onClick = { onClick(article) })
                }

        }
    }


@Composable
fun ArticlesList(
    modifier: Modifier=Modifier,
    article: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(article = article)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = article.itemCount) {
                article[it]?.let {
                    ArticleCard(article = it, onClick = { onClick })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    article: LazyPagingItems<Article>,
): Boolean {

    val loadState = article.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(
                error=error
            )
            false
        }
        article.itemCount==0 ->{
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }

    }
}