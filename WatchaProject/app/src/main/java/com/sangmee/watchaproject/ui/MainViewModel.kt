package com.sangmee.watchaproject.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.sangmee.watchaproject.datasource.local.FavoriteLocalDataSourceImpl
import com.sangmee.watchaproject.datasource.local.TrackLocalDataSourceImpl
import com.sangmee.watchaproject.datasource.remote.TrackRemoteDataSourceImpl
import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.repository.FavoriteRepositoryImpl
import com.sangmee.watchaproject.repository.TrackRepositoryImpl
import com.sangmee.watchaproject.service.AppDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db =
        Room.databaseBuilder(application, AppDatabase::class.java, "track-db").build()

    private val trackRepository by lazy {
        TrackRepositoryImpl(
            TrackRemoteDataSourceImpl(),
            TrackLocalDataSourceImpl(db)
        )
    }
    private val favoriteRepository by lazy { FavoriteRepositoryImpl(FavoriteLocalDataSourceImpl(db)) }

    private val compositeDisposable = CompositeDisposable()
    private val term = "greenday"
    private val entity = "song"
    private val limit = 30

    private val _tracks = MutableLiveData<List<Track>>()
    val tracks: LiveData<List<Track>>
        get() = _tracks

    private val _favorites = MutableLiveData<List<Track>>()
    val favorites: LiveData<List<Track>>
        get() = _favorites

    fun callTrack() {
        trackRepository.getTrack(term, entity, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cacheTrack(it.tracks)
            }, { t ->
                Log.e("error", t.message.toString())
                getCacheTrack()
            })
            .addTo(compositeDisposable)
    }

    fun getCacheTrack() {
        compositeDisposable.add(
            trackRepository.getAllTrackByRoom().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _tracks.value = it },
                    { t -> Log.e("cache_track_error", t.message.toString()) },
                    { _tracks.value = listOf() })
        )
    }

    private fun cacheTrack(tracks: List<Track>) {

        //새로운 데이터를 받았을 경우 기존 isFavorite은 유지하며 캐싱
        this.tracks.value?.let {
            for (track in tracks) {
                for (item in it) {
                    if (item.trackId == track.trackId) {
                        track.isFavorite = item.isFavorite
                        break
                    }
                }
            }
        }
        compositeDisposable.add(
            trackRepository.saveAndUpdateAll(tracks).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getCacheTrack()
                    Log.d("cache_track", "데이터 저장")
                },
                    { t -> Log.e("cache_track_error", t.message.toString()) })
        )
    }

    fun unbindViewModel() {
        compositeDisposable.clear()
    }
}
