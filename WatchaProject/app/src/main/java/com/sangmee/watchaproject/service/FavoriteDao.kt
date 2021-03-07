package com.sangmee.watchaproject.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sangmee.watchaproject.model.Track
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Track WHERE Track.isFavorite = 1 ORDER BY Track.trackId")
    fun getAllFavorite(): Maybe<List<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: Track): Completable
}
