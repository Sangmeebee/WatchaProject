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
import kotlinx.android.synthetic.main.fragment_track.*

class TrackFragment : Fragment() {

    private val trackAdapter = TrackAdapter()
    private val vm by activityViewModels<TrackViewModel>()

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

    private fun initView(){
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

    override fun onDestroy() {
        vm.unBindViewModel()
        super.onDestroy()
    }
}
