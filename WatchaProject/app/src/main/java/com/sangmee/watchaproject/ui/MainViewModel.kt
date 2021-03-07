package com.sangmee.watchaproject.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.repository.FavoriteRepository
import com.sangmee.watchaproject.repository.TrackRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel @ViewModelInject constructor(
    private val trackRepository: TrackRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

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

    val loadingSubject = BehaviorSubject.createDefault(false)

    fun callTrack() {
        trackRepository.getTrack(term, entity, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingSubject.onNext(true) }
            .doOnTerminate { loadingSubject.onNext(false) }
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

    fun getAllFavoriteTrack() {
        compositeDisposable.add(
            favoriteRepository.getAllFavoriteTrack().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ _favorites.value = it },
                    { t -> Log.e("cache_track_error", t.message.toString()) },
                    { _favorites.value = listOf() })
        )
    }

    fun updateFavoriteTrack(track: Track) {
        compositeDisposable.add(
            favoriteRepository.saveFavoriteTrack(track).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (track.isFavorite!!) {
                        Log.d("save_favorite", "즐겨찾기 저장")
                    } else {
                        Log.d("delete_favorite", "즐겨찾기 삭제")
                    }
                },
                    { t -> Log.e("save_favorite_error", t.message.toString()) })
        )
    }

    fun unbindViewModel() {
        compositeDisposable.clear()
    }
}
