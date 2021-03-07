package com.sangmee.watchaproject.ui.track

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sangmee.watchaproject.datasource.remote.TrackRemoteDataSourceImpl
import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.repository.TrackRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class TrackViewModel : ViewModel() {

    private val trackRepository by lazy { TrackRepositoryImpl(TrackRemoteDataSourceImpl()) }

    private val compositeDisposable = CompositeDisposable()
    private val term = "greenday"
    private val entity = "song"
    private val limit = 30

    private val _tracks = MutableLiveData<List<Track>>()
    val tracks: LiveData<List<Track>>
        get() = _tracks

    fun callTrack() {
        trackRepository.getTrack(term, entity, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _tracks.value = it.tracks }, { t -> Log.e("error", t.message.toString()) })
            .addTo(compositeDisposable)

    }

    fun unBindViewModel() {
        compositeDisposable.clear()
    }
}
