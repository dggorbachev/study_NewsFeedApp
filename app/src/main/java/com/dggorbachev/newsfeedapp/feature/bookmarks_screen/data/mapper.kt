package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data

import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local.BookmarkEntity
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

fun Article.toEntityModel() =
    BookmarkEntity(
        url = url,
        author = author,
        title = title,
        description = description,
        publishedAt = publishedAt,
        urlToImage = urlToImage,
        isFavorite = isFavorite
    )

fun BookmarkEntity.toDomainModel() =
    Article(
        url = url,
        author = author,
        title = title,
        description = description,
        publishedAt = publishedAt,
        urlToImage = urlToImage,
        isFavorite = isFavorite
    )