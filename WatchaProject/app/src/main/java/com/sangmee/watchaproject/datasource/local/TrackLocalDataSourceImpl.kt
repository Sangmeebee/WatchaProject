package com.sangmee.watchaproject.datasource.local

import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.service.TrackDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import javax.inject.Singleton

class TrackLocalDataSourceImpl @Inject constructor(private val trackDao: TrackDao) :
    TrackLocalDataSource {

    override fun saveAndUpdateAll(tracks: List<Track>): Completable {
        return trackDao.insertAll(tracks)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class TrackLocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindTrackLocalDataSource(trackLocalDataSourceImpl: TrackLocalDataSourceImpl): TrackLocalDataSource
}
