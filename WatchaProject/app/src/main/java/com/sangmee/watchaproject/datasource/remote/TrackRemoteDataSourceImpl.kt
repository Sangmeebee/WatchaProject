package com.sangmee.watchaproject.datasource.remote

import com.sangmee.watchaproject.model.Result
import com.sangmee.watchaproject.service.RetrofitClient
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

class TrackRemoteDataSourceImpl : TrackRemoteDataSource {

    override fun getTrack(term: String, entity: String, limit: Int): Single<Result> {
        return RetrofitClient.getService().getTrack(term, entity, limit)
    }
}
