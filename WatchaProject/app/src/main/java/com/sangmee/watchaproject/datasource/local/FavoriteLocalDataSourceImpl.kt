package com.sangmee.watchaproject.datasource.local

import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.service.FavoriteDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject
import javax.inject.Singleton

class FavoriteLocalDataSourceImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    FavoriteLocalDataSource {

    override fun getAllFavoriteTrack(): Maybe<List<Track>> {
        return favoriteDao.getAllFavorite()
    }

    override fun saveFavoriteTrack(track: Track): Completable {
        return favoriteDao.insert(track)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class FavoriteLocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteLocalDataSource(favoriteLocalDataSourceImpl: FavoriteLocalDataSourceImpl): FavoriteLocalDataSource
}
