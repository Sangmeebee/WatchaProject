package com.sangmee.watchaproject.extension

import android.widget.ImageView
import android.widget.ToggleButton
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sangmee.watchaproject.R
import com.sangmee.watchaproject.model.Track

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.ic_no_image)
        .error(R.drawable.ic_no_image).into(this)
}

@BindingAdapter("setIsFavorite", "setTrack")
fun ToggleButton.setIsFavorite(isFavorite: (Track) -> Unit, track: Track) {
    setOnClickListener {
        track.isFavorite = this.isChecked
        isFavorite(track)
    }
}
