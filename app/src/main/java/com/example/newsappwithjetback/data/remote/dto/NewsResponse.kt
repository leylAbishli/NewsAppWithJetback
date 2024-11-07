package com.example.newsappwithjetback.data.remote.dto

import com.example.newsappwithjetback.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)