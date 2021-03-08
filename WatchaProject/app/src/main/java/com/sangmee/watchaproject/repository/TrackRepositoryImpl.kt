package com.sangmee.watchaproject.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.sangmee.watchaproject.datasource.local.TrackLocalDataSource
import com.sangmee.watchaproject.datasource.local.TrackRxPagingSource
import com.sangmee.watchaproject.datasource.remote.TrackRemoteDataSource
import com.sangmee.watchaproject.model.Result
import com.sangmee.watchaproject.model.Track
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class TrackRepositoryImpl @Inject constructor(
    private val trackRemoteDataSource: TrackRemoteDataSource,
    private val trackLocalDataSource: TrackLocalDataSource,
    private val trackRxPagingSource: TrackRxPagingSource
) : TrackRepository {

    override fun getTrack(term: String, entity: String): Single<Result> {
        return trackRemoteDataSource.getTrack(term, entity)
    }

    override fun getAllTrackByRoom() =
        Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { trackRxPagingSource }
        ).flowable

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

