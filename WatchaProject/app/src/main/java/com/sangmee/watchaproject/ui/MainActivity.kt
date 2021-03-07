package com.sangmee.watchaproject.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.sangmee.watchaproject.R
import com.sangmee.watchaproject.ui.favorite.FavoriteFragment
import com.sangmee.watchaproject.ui.track.TrackFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val vm by viewModels<MainViewModel>()
    private val compositeDisposable = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        initViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        //BottomNavigationView 세팅
        supportFragmentManager.beginTransaction().add(R.id.fl_container, TrackFragment()).commit()

        nv_menu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.trackItem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, TrackFragment()).commit()
                    true
                }
                R.id.favoriteItem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, FavoriteFragment()).commit()
                    true
                }
                else -> false
            }
        }

        nv_menu.setOnNavigationItemReselectedListener {
        }
    }

    private fun initViewModel() {
        vm.getCacheTrack()

        vm.loadingSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                pb_loading.isVisible = it
            }
            .addTo(compositeDisposable)
    }

    private fun unbindViewModel() {
        compositeDisposable.clear()
        vm.unbindViewModel()
    }

    override fun onPause() {
        unbindViewModel()
        super.onPause()
    }
}
