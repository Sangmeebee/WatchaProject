package com.sangmee.watchaproject.repository

import com.sangmee.watchaproject.datasource.local.TrackLocalDataSource
import com.sangmee.watchaproject.datasource.remote.TrackRemoteDataSource
import com.sangmee.watchaproject.model.Result
import com.sangmee.watchaproject.model.Track
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class TrackRepositoryImpl @Inject constructor(
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


@Module
@InstallIn(ApplicationComponent::class)
abstract class TrackRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTrackRepository(trackRepositoryImpl: TrackRepositoryImpl): TrackRepository
}

