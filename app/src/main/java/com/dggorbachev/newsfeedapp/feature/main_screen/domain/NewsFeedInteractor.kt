package com.dggorbachev.newsfeedapp.feature.main_screen.domain

import com.dggorbachev.newsfeedapp.base.attempt
import com.dggorbachev.newsfeedapp.base.mapToList
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.BookmarksRepo
import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.NewsRepo
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

class NewsFeedInteractor(
    private val newsRepo: NewsRepo,
    private val bookmarksRepo: BookmarksRepo
) {
    suspend fun getNews() = attempt {
        mapToList(newsRepo.getNews(), bookmarksRepo.read())
    }
//    : List<Article>
}