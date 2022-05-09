package com.dggorbachev.newsfeedapp.feature.main_screen.data

import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model.ArticleModel
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

fun ArticleModel.toArticle(): Article {
    return Article(
        author = author,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}