package com.example.newsappwithjetback.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsappwithjetback.data.remote.dto.NewsResponse
import com.example.newsappwithjetback.domain.model.Article
import com.example.newsappwithjetback.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {

        return newsRepository.getNews(sources = sources)

    }
}