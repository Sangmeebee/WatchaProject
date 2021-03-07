package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.model.Result
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface TrackRepository {

    fun getTrack(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int
    ): Single<Result>

    fun getAllTrackByRoom(): Maybe<List<Track>>
    fun saveAndUpdateAll(tracks: List<Track>): Completable
}
