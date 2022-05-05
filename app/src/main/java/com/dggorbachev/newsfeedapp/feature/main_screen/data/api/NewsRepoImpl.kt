package com.dggorbachev.newsfeedapp.feature.main_screen.data.api

import com.dggorbachev.newsfeedapp.feature.main_screen.data.toArticle
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

class NewsRepoImpl(private val source: NewsRemoteSource) : NewsRepo {
    override suspend fun getNews(): List<Article> {
        return source.getNews().articles.map {
            it.toArticle()
        }
    }
}