package com.dggorbachev.newsfeedapp.feature.main_screen.data.api

import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model.ResponseObjectModel

class NewsRemoteSource(private val api: NewsApi) {
    suspend fun getNews(): ResponseObjectModel {
        return api.getTopHeadlinesNews()
    }
}