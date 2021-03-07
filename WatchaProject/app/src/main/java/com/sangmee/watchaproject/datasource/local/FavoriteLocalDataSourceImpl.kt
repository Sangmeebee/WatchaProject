package com.sangmee.watchaproject.datasource.local

import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.service.AppDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class FavoriteLocalDataSourceImpl(private val db: AppDatabase) : FavoriteLocalDataSource {

    override fun getAllFavoriteTrack(): Maybe<List<Track>> {
        return db.favoriteDao().getAllFavorite()
    }

    override fun saveFavoriteTrack(track: Track): Completable {
        return db.favoriteDao().insert(track)
    }
}
