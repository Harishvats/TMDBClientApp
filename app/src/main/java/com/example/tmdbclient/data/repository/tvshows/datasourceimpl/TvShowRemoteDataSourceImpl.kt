package com.example.tmdbclient.data.repository.tvshows.datasourceimpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tvshow.TvShowList
import com.example.tmdbclient.data.repository.tvshows.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String) :
    TvShowRemoteDataSource {

    override suspend fun getTvShows(): Response<TvShowList> = tmdbService.getPopularTvShows(apiKey)

}