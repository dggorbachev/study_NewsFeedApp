package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.ui

import com.dggorbachev.newsfeedapp.base.Event
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

data class ViewState(
    val articleList: List<Article>
)

sealed class UiEvent : Event {
    data class OnBookmarksFetched(val articles: List<Article>) : UiEvent()
    data class OnBookmarkClick(val article: Article) : UiEvent()
}

sealed class DataEvent : Event {
    object RefreshDataBase : DataEvent()
}

sealed class OpenArticleEvent : Event {
    data class OnArticleClick(val article: Article) : OpenArticleEvent()
}