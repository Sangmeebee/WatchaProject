package com.sangmee.watchaproject.ui.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.watchaproject.R
import com.sangmee.watchaproject.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val trackAdapter = TrackAdapter()
    private val vm by activityViewModels<MainViewModel>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }


    private fun initView() {

        rv_track.apply {
            adapter = trackAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun initViewModel() {
        compositeDisposable.add(vm.getAllFavoriteTrack().subscribe {
            trackAdapter.submitData(lifecycle, it)
        })
    }

//    override fun onClickToggleBtn(track: Track, isFavorite: Boolean) {
//        track.isFavorite = isFavorite
//        vm.updateFavoriteTrack(track)
//    }

    override fun onDestroy() {
        vm.unbindViewModel()
        super.onDestroy()
    }
}
