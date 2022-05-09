package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookmarksDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM ${BookmarkEntity.TABLE_NAME}")
    suspend fun read(): List<BookmarkEntity>

    @Update
    suspend fun update(bookmarkEntity: BookmarkEntity)

    @Delete
    suspend fun delete(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM ${BookmarkEntity.TABLE_NAME} ORDER BY url DESC")
    fun subscribe(): LiveData<List<BookmarkEntity>>
}