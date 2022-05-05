package com.dggorbachev.newsfeedapp.feature.main_screen.data.api

import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.model.ResponseObjectModel
import retrofit2.http.GET
import retrofit2.http.Query

// api key e9008ca813cd40dc972694be293c8e90

// everything GET https://newsapi.org/v2/everything?q=bitcoin&apiKey=e9008ca813cd40dc972694be293c8e90
// top-headlines GET https://newsapi.org/v2/top-headlines?country=us&apiKey=e9008ca813cd40dc972694be293c8e90

interface NewsApi {

    @GET("v2/everything")
    suspend fun getEverythingNews(
        @Query("apiKey") apiKey: String = "e9008ca813cd40dc972694be293c8e90",
        @Query("q") query: String = "bitcoin",
        @Query("sources") sources: String = "",
        @Query("domains") domains: String = "",
        @Query("excludeDomains") excludeDomains: String = "",
        @Query("from") from: String = "",
        @Query("to") to: String = "",
        @Query("language") language: String = "us",
        @Query("sortBy") sortBy: String = "",
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 1,
    ): ResponseObjectModel

    @GET("v2/top-headlines")
    suspend fun getTopHeadlinesNews(
        @Query("apiKey") apiKey: String = "e9008ca813cd40dc972694be293c8e90",
        @Query("country") country: String = "ru",
        @Query("category") category: String = "",
        @Query("sources") sources: String = "",
        @Query("q") query: String = "",
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 1,
    ): ResponseObjectModel
}