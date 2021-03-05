package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.datasource.remote.TrackRemoteDataSource
import com.sangmee.watchaproject.model.Result
import io.reactivex.rxjava3.core.Single

class TrackRepositoryImpl(private val trackRemoteDataSource: TrackRemoteDataSource) :
    TrackRepository {

    override fun getTrack(term: String, entity: String, limit: Int): Single<Result> {
        return trackRemoteDataSource.getTrack(term, entity, limit)
    }
}
