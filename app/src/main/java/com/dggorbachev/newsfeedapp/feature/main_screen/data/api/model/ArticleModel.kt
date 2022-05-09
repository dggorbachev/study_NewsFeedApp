package com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class ArticleModel(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String? = ""
)