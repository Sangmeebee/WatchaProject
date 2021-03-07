package com.sangmee.watchaproject.datasource.local

import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.service.AppDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class TrackLocalDataSourceImpl(private val db: AppDatabase) : TrackLocalDataSource {

    override fun getAllTrackByRoom(): Maybe<List<Track>> {
        return db.trackDao().getAll()
    }

    override fun saveAndUpdateAll(tracks: List<Track>): Completable {
        return db.trackDao().insertAll(tracks)
    }
}
