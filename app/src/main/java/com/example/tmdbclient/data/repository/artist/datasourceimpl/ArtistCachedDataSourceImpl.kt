package com.example.tmdbclient.data.repository.artist.datasourceimpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCachedDataSource

class ArtistsCachedDataSourceImpl : ArtistCachedDataSource {
    private var artistsList = ArrayList<Artist>()
    override suspend fun getArtistsFromCache(): List<Artist> = artistsList

    override suspend fun saveArtistsToCache(artists: List<Artist>) {
        artistsList.clear()
        artistsList = ArrayList(artists)
    }
}