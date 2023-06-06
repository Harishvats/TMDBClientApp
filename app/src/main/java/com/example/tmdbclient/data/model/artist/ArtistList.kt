package com.example.tmdbclient.data.model.artist

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArtistList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val people: List<Artist>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)