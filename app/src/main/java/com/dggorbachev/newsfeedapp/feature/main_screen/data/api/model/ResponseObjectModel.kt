package com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseObjectModel(
    @SerializedName("articles")
    val articles: List<ArticleModel>,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int
)