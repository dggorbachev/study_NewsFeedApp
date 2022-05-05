package com.dggorbachev.newsfeedapp.feature.main_screen.di

import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.NewsApi
import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.NewsRemoteSource
import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.NewsRepo
import com.dggorbachev.newsfeedapp.feature.main_screen.data.api.NewsRepoImpl
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.NewsFeedInteractor
import com.dggorbachev.newsfeedapp.feature.main_screen.ui.MainScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://newsapi.org/"

val mainScreenModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }

    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single<NewsApi> {
        get<Retrofit>().create(NewsApi::class.java)
    }

    single<NewsRemoteSource> {
        NewsRemoteSource(get<NewsApi>())
    }

    single<NewsRepo> {
        NewsRepoImpl(get<NewsRemoteSource>())
    }

    single<NewsFeedInteractor> {
        NewsFeedInteractor(get<NewsRepo>())
    }

    viewModel<MainScreenViewModel> {
        MainScreenViewModel(get<NewsFeedInteractor>())
    }
}