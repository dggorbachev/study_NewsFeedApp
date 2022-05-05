package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import com.dggorbachev.newsfeedapp.base.Event
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

data class ViewState(
    val articleList: List<Article>,
    val errorMessage: String?,
    val isLoading: Boolean
)

sealed class UiEvent() : Event {
    class OnArticleClick : UiEvent()
}

sealed class DataEvent() : Event {
    object StartLoadData : DataEvent()
    object OnLoadData : DataEvent()
    data class SuccessNewsRequest(val articleList: List<Article>) : DataEvent()
    data class ErrorNewsRequest(val errorMessage: String) : DataEvent()
}