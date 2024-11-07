package com.example.newsappwithjetback.domain.usecases.news

import com.example.newsappwithjetback.data.local.NewsDao
import com.example.newsappwithjetback.domain.model.Article
import com.example.newsappwithjetback.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String):Article? {
       return  newsRepository.selectArticle(url)
    }
}