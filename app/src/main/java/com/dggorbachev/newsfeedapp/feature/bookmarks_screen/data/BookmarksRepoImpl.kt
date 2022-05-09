package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksDAO
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

class BookmarksRepoImpl(private val bookmarksDAO: BookmarksDAO) : BookmarksRepo {
    override suspend fun create(article: Article) {
        bookmarksDAO.create(article.toEntityModel())
    }

    override suspend fun read(): List<Article> {
        return bookmarksDAO.read().map { it.toDomainModel() }
    }

    override suspend fun update(article: Article) {
        bookmarksDAO.update(article.toEntityModel())
    }

    override suspend fun delete(article: Article) {
        bookmarksDAO.delete(article.toEntityModel())
    }

    override suspend fun subscribe(): LiveData<List<Article>> {
        return bookmarksDAO.subscribe().map { it -> it.map { it.toDomainModel() } }
    }
}