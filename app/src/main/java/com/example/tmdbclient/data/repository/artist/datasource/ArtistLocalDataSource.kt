package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistLocalDataSource {
    suspend fun getArtistsFromDb(): List<Artist>
    suspend fun saveArtistsToDb(movie: List<Artist>)
    suspend fun clearAll()
}