package com.example.newsappwithjetback.domain.usecases.news

data class NewsUseCase(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle:UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle:SelectArticle
)
