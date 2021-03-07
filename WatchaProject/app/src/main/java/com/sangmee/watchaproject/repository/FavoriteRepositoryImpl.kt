package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.datasource.local.FavoriteLocalDataSource
import com.sangmee.watchaproject.model.Track
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject
import javax.inject.Singleton

class FavoriteRepositoryImpl @Inject constructor(private val favoriteLocalDataSource: FavoriteLocalDataSource) :
    FavoriteRepository {

    override fun getAllFavoriteTrack(): Maybe<List<Track>> {
        return favoriteLocalDataSource.getAllFavoriteTrack()
    }

    override fun saveFavoriteTrack(track: Track): Completable {
        return favoriteLocalDataSource.saveFavoriteTrack(track)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class FavoriteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}
