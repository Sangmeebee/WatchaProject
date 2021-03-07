package com.sangmee.watchaproject.ui.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sangmee.watchaproject.R
import com.sangmee.watchaproject.databinding.TrackItemBinding
import com.sangmee.watchaproject.model.Track
import kotlinx.android.synthetic.main.track_item.view.*

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    private val trackList = arrayListOf<Track>()
    var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = DataBindingUtil.inflate<TrackItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.track_item,
            parent,
            false
        )

        val viewHolder = TrackViewHolder(binding)

        viewHolder.itemView.tb_favorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                trackList[viewHolder.adapterPosition].let {
                    onClickListener?.onClickToggleBtn(it, true)
                }
            } else {
                trackList[viewHolder.adapterPosition].let {
                    onClickListener?.onClickToggleBtn(it, false)
                }
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(trackList[position])
    }

    override fun getItemCount() = trackList.size

    fun clearAndAddTracks(tracks: List<Track>) {
        trackList.clear()
        trackList.addAll(tracks)
        notifyDataSetChanged()
    }


    interface OnClickListener {
        fun onClickToggleBtn(track: Track, isFavorite: Boolean)
    }

    class TrackViewHolder(private val binding: TrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()
        }
    }
}
