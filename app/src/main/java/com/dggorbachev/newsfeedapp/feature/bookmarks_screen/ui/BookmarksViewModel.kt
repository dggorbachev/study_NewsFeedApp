package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.ui

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.dggorbachev.newsfeedapp.base.BaseViewModel
import com.dggorbachev.newsfeedapp.base.Event
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.domain.BookmarksInteractor
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookmarksViewModel(private val bookmarksInteractor: BookmarksInteractor) :
    BaseViewModel<ViewState>() {

    init {
        viewModelScope.launch {
            bookmarksInteractor.subscribe().asFlow().collect {
                processUiEvent(UiEvent.OnBookmarksFetched(articles = it))
            }
        }
        processDataEvent(DataEvent.RefreshDataBase)
    }

    override fun initialViewState(): ViewState {
        return ViewState(articleList = emptyList())
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.RefreshDataBase -> {
                val newArticleList = bookmarksInteractor.read()
                return previousState.copy(articleList = newArticleList)
            }
            is UiEvent.OnBookmarksFetched -> {
                return previousState.copy(articleList = event.articles)
            }
            is UiEvent.OnBookmarkClick -> {
                bookmarksInteractor.delete(event.article)
            }
            is OpenArticleEvent.OnArticleClick -> {
            }
            //else -> return null
        }
        return null
    }
}