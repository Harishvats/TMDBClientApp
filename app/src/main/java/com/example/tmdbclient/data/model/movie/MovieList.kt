package com.example.tmdbclient.data.model.movie

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>
)