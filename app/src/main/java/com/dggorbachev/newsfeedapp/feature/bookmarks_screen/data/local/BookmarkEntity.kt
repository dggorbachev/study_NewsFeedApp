package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local.BookmarkEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
) {
    companion object {
        const val TABLE_NAME = "BOOKMARKS_TABLE"
    }
}