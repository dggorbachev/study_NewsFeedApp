package com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class ArticleModel(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("content")
    val content: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("source")
    val source: SourceModel,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String? = "",

)