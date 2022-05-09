package com.dggorbachev.newsfeedapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local.BookmarkEntity
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local.BookmarksDAO

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookmarksDAO(): BookmarksDAO
}