package com.sangmee.watchaproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.service.FavoriteDao
import com.sangmee.watchaproject.service.TrackDao

@Database(entities = [Track::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun trackDao(): TrackDao
}
