package com.dggorbachev.newsfeedapp

import android.app.Application
import com.dggorbachev.newsfeedapp.di.appModule
import com.dggorbachev.newsfeedapp.di.dataBaseModule
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.di.bookmarksScreenModule
import com.dggorbachev.newsfeedapp.feature.main_screen.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, mainScreenModule, bookmarksScreenModule, dataBaseModule)
        }
    }
}