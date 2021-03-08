package com.sangmee.watchaproject.repository

import androidx.paging.PagingData
import com.sangmee.watchaproject.model.Result
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface TrackRepository {

    fun getTrack(
        @Query("term") term: String,
        @Query("entity") entity: String,
    ): Single<Result>

    fun getAllTrackByRoom(): Flowable<PagingData<Track>>
    fun saveAndUpdateAll(tracks: List<Track>): Completable
}
