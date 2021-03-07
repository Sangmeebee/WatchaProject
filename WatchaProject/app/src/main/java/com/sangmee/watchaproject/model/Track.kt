package com.sangmee.watchaproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Track(

    @PrimaryKey
    @SerializedName("trackId")
    @Expose
    val trackId: Int,
    @SerializedName("artistName")
    @Expose
    val artistName: String,
    @SerializedName("collectionName")
    @Expose
    val collectionName: String,
    @SerializedName("trackName")
    @Expose
    val trackName: String,
    @SerializedName("artworkUrl60")
    @Expose
    var artwork: String,
    var isFavorite: Boolean?
)
