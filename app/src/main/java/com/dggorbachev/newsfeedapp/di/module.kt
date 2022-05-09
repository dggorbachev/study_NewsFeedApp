package com.dggorbachev.newsfeedapp.di

import androidx.room.Room
import com.dggorbachev.newsfeedapp.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DATA_BASE = "DATA_BASE"

val appModule = module {

}

val dataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, DATA_BASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AppDataBase>().bookmarksDAO()
    }
}