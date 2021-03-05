package com.sangmee.watchaproject.ui.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.watchaproject.R
import com.sangmee.watchaproject.databinding.TrackItemBinding
import com.sangmee.watchaproject.model.Track

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    private val trackList = arrayListOf<Track>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<TrackItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.track_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trackList[position])
    }

    override fun getItemCount() = trackList.size

    fun clearAndAddTracks(tracks: List<Track>) {
        trackList.clear()
        trackList.addAll(tracks)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: TrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()
        }
    }
}
