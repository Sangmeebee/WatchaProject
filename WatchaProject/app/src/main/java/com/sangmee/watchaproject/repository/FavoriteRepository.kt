package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface FavoriteRepository {

    fun getAllFavoriteTrack(): Maybe<List<Track>>
    fun saveFavoriteTrack(track: Track): Completable
}
