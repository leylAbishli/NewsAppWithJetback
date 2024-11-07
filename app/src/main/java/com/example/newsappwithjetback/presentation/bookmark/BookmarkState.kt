package com.example.newsappwithjetback.presentation.bookmark

import com.example.newsappwithjetback.domain.model.Article

data class BookmarkState (
    val articles:List<Article> = emptyList()
)