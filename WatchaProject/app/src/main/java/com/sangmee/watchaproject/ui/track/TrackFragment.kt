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
import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_track.*

@AndroidEntryPoint
class TrackFragment : Fragment() {

    private val trackAdapter = TrackAdapter(::setIsFavorite)
    private val vm by activityViewModels<MainViewModel>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track, container, false)
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
        compositeDisposable.add(vm.getCacheTrack().subscribe {
            trackAdapter.submitData(lifecycle, it)
        })
    }

    private fun setIsFavorite(track: Track) {
        vm.updateFavoriteTrack(track)
    }

    override fun onDestroyView() {
        vm.unbindViewModel()
        compositeDisposable.clear()
        super.onDestroyView()
    }
}
