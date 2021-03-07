package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.datasource.local.FavoriteLocalDataSource
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class FavoriteRepositoryImpl(private val favoriteLocalDataSource: FavoriteLocalDataSource) :
    FavoriteRepository {

    override fun getAllFavoriteTrack(): Maybe<List<Track>> {
        return favoriteLocalDataSource.getAllFavoriteTrack()
    }

    override fun saveFavoriteTrack(track: Track): Completable {
        return favoriteLocalDataSource.saveFavoriteTrack(track)
    }
}
