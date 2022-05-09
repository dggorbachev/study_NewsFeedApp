package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.dggorbachev.newsfeedapp.base.BaseViewModel
import com.dggorbachev.newsfeedapp.base.Event
import com.dggorbachev.newsfeedapp.base.mapToList
import com.dggorbachev.newsfeedapp.base.openWeb
import com.dggorbachev.newsfeedapp.base.utils.SingleLiveEvent
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.domain.BookmarksInteractor
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.NewsFeedInteractor
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val newsFeedInteractor: NewsFeedInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    val openWebEvent = SingleLiveEvent<OpenArticleEvent.OnArticleClick>()

    init {
        viewModelScope.launch {
            bookmarksInteractor.subscribe().asFlow().collect {
                processUiEvent(UiEvent.OnBookmarksFetched(articles = it))
            }
        }
        processDataEvent(DataEvent.OnLoadData)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            articleList = emptyList(),  // or listOf()
            errorMessage = "",
            isLoading = false
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnBookmarkClick -> {
                bookmarksInteractor.create(event.article.copy(isFavorite = true))
            }
            is OpenArticleEvent.OnArticleClick -> {
                openWebEvent.value = event
            }
            is DataEvent.OnLoadData -> {
                processDataEvent(DataEvent.StartLoadData)
                newsFeedInteractor.getNews().fold(
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorNewsRequest(
                                it.localizedMessage ?: ""
                            )
                        )
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessNewsRequest(articleList = it))
                    }
                )
            }
            is DataEvent.StartLoadData -> {
                return previousState.copy(isLoading = true)
            }
            is UiEvent.OnBookmarksFetched -> {
                val oldArticles = previousState.articleList
                val newArticles = event.articles

                val articles = mapToList(oldList = oldArticles, newList = newArticles)
                return previousState.copy(articleList = articles)
            }
            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(articleList = event.articleList, isLoading = false)
            }
            is DataEvent.ErrorNewsRequest -> {
            }
        }
        return null
    }
}