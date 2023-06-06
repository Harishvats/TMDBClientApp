package com.example.tmdbclient.data.repository.tvshows

import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshows.datasource.TvShowCachedDataSource
import com.example.tmdbclient.data.repository.tvshows.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshows.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowsRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCachedDataSource
) : TvShowsRepository {

    override suspend fun getTvShows(): List<TvShow>? {


        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {


        val newListTvShows = getTvShowsFromApi()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDb(newListTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListTvShows)
        return newListTvShows
    }

    private suspend fun getTvShowsFromApi(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body != null) {
                tvShowList = body.tvShows
            }

        } catch (exception: Exception) {
            tvShowList = emptyList()
        }
        return tvShowList

    }

    private suspend fun getTvShowsFromDb(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        tvShowList = try {
            tvShowLocalDataSource.getTvShowsFromDb()
        } catch (exception: Exception) {
            emptyList()
        }
        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromApi()
            tvShowLocalDataSource.saveTvShowsToDb(tvShowList)
        }
        return tvShowList

    }

    private suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        tvShowList = try {
            tvShowCacheDataSource.getTvShowsFromCache()
        } catch (exception: Exception) {
            emptyList()
        }
        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromDb()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return tvShowList
    }
}