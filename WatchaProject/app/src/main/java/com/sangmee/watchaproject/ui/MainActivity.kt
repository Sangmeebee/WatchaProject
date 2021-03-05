package com.sangmee.watchaproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sangmee.watchaproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        //BottomNavigationView 세팅
        nv_menu.post { nv_menu.selectedItemId = R.id.trackItem }

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
    }
}
