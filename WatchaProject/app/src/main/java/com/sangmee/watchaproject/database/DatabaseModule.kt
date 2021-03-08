package com.sangmee.watchaproject.database

import android.content.Context
import androidx.room.Room
import com.sangmee.watchaproject.service.FavoriteDao
import com.sangmee.watchaproject.service.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }

    @Provides
    fun provideTrackDao(appDatabase: AppDatabase): TrackDao {
        return appDatabase.trackDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "track-db").build()
    }
}
