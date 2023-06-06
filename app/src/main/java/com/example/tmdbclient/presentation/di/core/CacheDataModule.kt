package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.datasource.ArtistCachedDataSource
import com.example.tmdbclient.data.repository.artist.datasourceimpl.ArtistsCachedDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCachedDataSource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieCachedDataSourceImpl
import com.example.tmdbclient.data.repository.tvshows.datasource.TvShowCachedDataSource
import com.example.tmdbclient.data.repository.tvshows.datasourceimpl.TvShowCachedDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {

    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCachedDataSource {
        return MovieCachedDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistCacheDataSource(): ArtistCachedDataSource {
        return ArtistsCachedDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource(): TvShowCachedDataSource {
        return TvShowCachedDataSourceImpl()
    }
}