package com.example.newsappwithjetback.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsappwithjetback.domain.model.Article
import com.example.newsappwithjetback.domain.repository.NewsRepository
import com.example.newsappwithjetback.presentation.search.SearchEvent
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery:String,sources: List<String>): Flow<PagingData<Article>> {

        return newsRepository.searchNews(searchQuery=searchQuery,sources = sources)

    }
}