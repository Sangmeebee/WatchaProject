package com.sangmee.watchaproject.ui.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.watchaproject.R
import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_track.*

class TrackFragment : Fragment(), TrackAdapter.OnClickListener {

    private val trackAdapter = TrackAdapter()
    private val vm by activityViewModels<MainViewModel>()

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
        trackAdapter.onClickListener = this

        rv_track.apply {
            adapter = trackAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun initViewModel() {
        vm.callTrack()

        vm.tracks.observe(viewLifecycleOwner, Observer {
            trackAdapter.clearAndAddTracks(it)
        })
    }

    override fun onClickToggleBtn(track: Track, isFavorite: Boolean) {
        track.isFavorite = isFavorite
        vm.updateFavoriteTrack(track)
    }

    override fun onDestroy() {
        vm.unbindViewModel()
        super.onDestroy()
    }
}
