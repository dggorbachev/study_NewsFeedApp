package com.dggorbachev.newsfeedapp.feature.main_screen.data

import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model.ArticleModel
import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model.SourceModel
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Source

fun SourceModel.toSource(): Source {
    return Source(
        id = id,
        name = name
    )
}

fun ArticleModel.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.toSource(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}