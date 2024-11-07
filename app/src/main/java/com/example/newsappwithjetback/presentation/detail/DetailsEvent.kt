package com.example.newsappwithjetback.presentation.detail

import com.example.newsappwithjetback.domain.model.Article

sealed class DetailsEvent{
    data class  UpsertDeleteArticle(val article: Article):DetailsEvent()
    object  RemoveSideEffect: DetailsEvent()

}
