package com.example.newsappwithjetback.domain.usecases.news

import com.example.newsappwithjetback.data.local.NewsDao
import com.example.newsappwithjetback.domain.model.Article
import com.example.newsappwithjetback.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
      return  newsRepository.selectArticles()
    }
}