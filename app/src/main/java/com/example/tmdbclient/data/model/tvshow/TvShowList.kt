package com.example.tmdbclient.data.model.tvshow

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TvShowList(
    @SerializedName("results")
    val tvShows: List<TvShow>
)