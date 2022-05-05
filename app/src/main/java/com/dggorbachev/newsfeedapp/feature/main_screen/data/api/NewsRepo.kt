package com.dggorbachev.newsfeedapp.feature.main_screen.data.api

import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

interface NewsRepo {
    suspend fun getNews(): List<Article>
}