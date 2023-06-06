package com.example.tmdbclient.data.repository.tvshows.datasource

import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDb(): List<TvShow>
    suspend fun saveTvShowsToDb(movie: List<TvShow>)
    suspend fun clearAll()
}