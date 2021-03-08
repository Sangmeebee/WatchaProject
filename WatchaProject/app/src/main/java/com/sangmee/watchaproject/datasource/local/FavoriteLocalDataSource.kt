package com.sangmee.watchaproject.datasource.local

import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface FavoriteLocalDataSource {

    fun saveFavoriteTrack(track: Track): Completable
}
