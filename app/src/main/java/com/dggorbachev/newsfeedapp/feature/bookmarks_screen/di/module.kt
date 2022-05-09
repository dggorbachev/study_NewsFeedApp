package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.di

import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.BookmarksRepo
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.BookmarksRepoImpl
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksDAO
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.domain.BookmarksInteractor
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.ui.BookmarksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksScreenModule = module {
    single<BookmarksRepo> {
        BookmarksRepoImpl(get<BookmarksDAO>())
    }

    single<BookmarksInteractor> {
        BookmarksInteractor(get<BookmarksRepo>())
    }

    viewModel<BookmarksViewModel> {
        BookmarksViewModel(get<BookmarksInteractor>())
    }
}