package com.sangmee.watchaproject.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sangmee.watchaproject.model.Track

@Database(entities = [Track::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun trackDao(): TrackDao
}
