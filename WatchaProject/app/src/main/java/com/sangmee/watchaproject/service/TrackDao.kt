package com.sangmee.watchaproject.service

import androidx.room.*
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface TrackDao {

    @Transaction
    @Query("SELECT * FROM Track ORDER BY Track.trackId")
    fun getAll(): Maybe<List<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(tracks: List<Track>): Completable

}
