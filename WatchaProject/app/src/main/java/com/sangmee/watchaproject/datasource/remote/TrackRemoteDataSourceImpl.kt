package com.sangmee.watchaproject.datasource.remote

import com.sangmee.watchaproject.model.Result
import com.sangmee.watchaproject.service.RetrofitClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

class TrackRemoteDataSourceImpl @Inject constructor() : TrackRemoteDataSource {

    override fun getTrack(term: String, entity: String): Single<Result> {
        return RetrofitClient.getService().getTrack(term, entity)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class TrackRemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindTrackRemoteDataSource(trackRemoteDataSourceImpl: TrackRemoteDataSourceImpl): TrackRemoteDataSource
}
