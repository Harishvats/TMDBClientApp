package com.example.tmdbclient.data.repository.tvshows.datasource

import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowCachedDataSource {
    suspend fun getTvShowsFromCache(): List<TvShow>
    suspend fun saveTvShowsToCache(tvShows: List<TvShow>)

}