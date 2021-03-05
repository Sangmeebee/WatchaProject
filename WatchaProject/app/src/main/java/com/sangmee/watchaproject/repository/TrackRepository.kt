package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.model.Result
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface TrackRepository {

    fun getTrack(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int
    ): Single<Result>
}
