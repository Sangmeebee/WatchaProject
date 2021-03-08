package com.sangmee.watchaproject.ui.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.watchaproject.R
import com.sangmee.watchaproject.databinding.TrackItemBinding
import com.sangmee.watchaproject.model.Track

class TrackAdapter : PagingDataAdapter<Track, TrackAdapter.TrackViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = DataBindingUtil.inflate<TrackItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.track_item,
            parent,
            false
        )

        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class TrackViewHolder(private val binding: TrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem.trackId == newItem.trackId
            }

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem == newItem
            }
        }
    }
}
