package com.sangmee.watchaproject.repository

import androidx.paging.PagingData
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface FavoriteRepository {

    fun getAllFavoriteTrack(): Flowable<PagingData<Track>>
    fun saveFavoriteTrack(track: Track): Completable
}
