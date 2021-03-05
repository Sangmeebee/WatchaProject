package com.sangmee.watchaproject.service

import com.sangmee.watchaproject.model.Result
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET(".")
    fun getTrack(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int
    ): Single<Result>
}
