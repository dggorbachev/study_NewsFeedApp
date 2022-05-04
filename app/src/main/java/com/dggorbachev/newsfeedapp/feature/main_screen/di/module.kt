package com.dggorbachev.newsfeedapp.feature.main_screen.di

import com.dggorbachev.newsfeedapp.feature.main_screen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel<MainScreenViewModel> {
        MainScreenViewModel()
    }
}