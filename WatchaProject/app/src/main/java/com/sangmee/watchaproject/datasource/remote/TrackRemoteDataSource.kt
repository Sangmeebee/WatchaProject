package com.sangmee.watchaproject.datasource.remote

import com.sangmee.watchaproject.model.Result
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface TrackRemoteDataSource {

    fun getTrack(
        @Query("term") term: String,
        @Query("entity") entity: String
    ): Single<Result>
}
