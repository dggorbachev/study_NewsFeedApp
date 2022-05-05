package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import com.dggorbachev.newsfeedapp.base.BaseViewModel
import com.dggorbachev.newsfeedapp.base.Event
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.NewsFeedInteractor

class MainScreenViewModel(private val newsFeedInteractor: NewsFeedInteractor) :
    BaseViewModel<ViewState>() {

    init {
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
            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(articleList = event.articleList, isLoading = false)
            }
            is DataEvent.ErrorNewsRequest -> {
            }
        }
        return null
    }

}