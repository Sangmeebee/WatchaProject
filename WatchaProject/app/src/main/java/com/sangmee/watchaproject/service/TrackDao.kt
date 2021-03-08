package com.sangmee.watchaproject.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TrackDao {

    @Query("SELECT * FROM Track LIMIT :limit OFFSET :offset")
    fun getAll(limit: Int, offset: Int): Single<List<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(tracks: List<Track>): Completable

}
