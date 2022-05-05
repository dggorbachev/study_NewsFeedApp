package com.dggorbachev.newsfeedapp.feature.main_screen.domain

import com.dggorbachev.newsfeedapp.base.attempt
import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.NewsRepo
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

class NewsFeedInteractor(private val repo: NewsRepo) {
    suspend fun getNews() = attempt {
        repo.getNews()
    }
//    : List<Article>
}