package com.sangmee.watchaproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("resultCount")
    @Expose
    val resultCount: Int,
    @SerializedName("results")
    @Expose
    val tracks: List<Track>
)
