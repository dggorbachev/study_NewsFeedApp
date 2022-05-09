package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data

import androidx.lifecycle.LiveData
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

interface BookmarksRepo {
    suspend fun create(article: Article)
    suspend fun read(): List<Article>
    suspend fun update(article: Article)
    suspend fun delete(article: Article)
    suspend fun subscribe(): LiveData<List<Article>>
}