package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.domain

import androidx.lifecycle.LiveData
import com.dggorbachev.newsfeedapp.base.attempt
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.BookmarksRepo
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

class BookmarksInteractor(private val repo: BookmarksRepo) {
    suspend fun create(article: Article) = repo.create(article = article)
    suspend fun read(): List<Article> = repo.read()
    suspend fun update(article: Article) = repo.update(article = article)
    suspend fun delete(article: Article) = attempt { repo.delete(article = article) }
    suspend fun subscribe(): LiveData<List<Article>> = repo.subscribe()
}