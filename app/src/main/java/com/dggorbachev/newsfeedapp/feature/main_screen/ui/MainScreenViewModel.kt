package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import com.dggorbachev.newsfeedapp.base.BaseViewModel
import com.dggorbachev.newsfeedapp.base.Event
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.NewsFeedInteractor

class MainScreenViewModel(private val newsFeedInteractor: NewsFeedInteractor) :
    BaseViewModel<ViewState>() {

    init {
        processUiEvent(UiEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(emptyList(), false)
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.GetCurrentNews -> {
                processDataEvent(DataEvent.OnLoadData)
                newsFeedInteractor.getNews().fold(
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorNewsRequest(
                                it.localizedMessage ?: ""
                            )
                        )
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessNewsRequest(it))
                    }
                )
            }
            is DataEvent.OnLoadData -> {
                return previousState.copy(isLoading = true)
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