package com.sangmee.watchaproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Track(

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
    var artwork: String
)
