package com.dggorbachev.newsfeedapp.feature.main_screen.domain.model

data class Article(
    val author: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String,
    val urlToImage: String?,
    val isFavorite: Boolean = false
)