package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.datasource.local.TrackLocalDataSource
import com.sangmee.watchaproject.datasource.remote.TrackRemoteDataSource
import com.sangmee.watchaproject.model.Result
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class TrackRepositoryImpl(
    private val trackRemoteDataSource: TrackRemoteDataSource,
    private val trackLocalDataSource: TrackLocalDataSource
) : TrackRepository {

    override fun getTrack(term: String, entity: String, limit: Int): Single<Result> {
        return trackRemoteDataSource.getTrack(term, entity, limit)
    }

    override fun getAllTrackByRoom(): Maybe<List<Track>> {
        return trackLocalDataSource.getAllTrackByRoom()
    }

    override fun saveAndUpdateAll(tracks: List<Track>): Completable {
        return trackLocalDataSource.saveAndUpdateAll(tracks)
    }
}
