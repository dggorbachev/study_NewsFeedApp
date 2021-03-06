package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import com.dggorbachev.newsfeedapp.base.Event
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

data class ViewState(
    val articleList: List<Article>,
    val searchResult: List<Article>,
    val errorMessage: String?,
    val isLoading: Boolean,
    val isSearchVisible: Boolean,
    val searchText: String
)

sealed class UiEvent : Event {
    data class OnBookmarkClick(val article: Article) : UiEvent()
    data class OnBookmarksFetched(val articles: List<Article>) : UiEvent()
    object OnSearchClicked : UiEvent()
    data class OnSearchTextInput(val searchText: String) : UiEvent()
}

sealed class DataEvent : Event {
    object StartLoadData : DataEvent()
    object OnLoadData : DataEvent()
    data class SuccessNewsRequest(val articleList: List<Article>) : DataEvent()
    data class ErrorNewsRequest(val errorMessage: String) : DataEvent()
}

sealed class OpenArticleEvent : Event {
    data class OnArticleClick(val article: Article) : UiEvent()
}