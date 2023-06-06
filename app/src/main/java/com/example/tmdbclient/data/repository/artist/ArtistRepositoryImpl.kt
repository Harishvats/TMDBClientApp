package com.example.tmdbclient.data.repository.artist

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCachedDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCachedDataSource: ArtistCachedDataSource
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {


        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {


        val newListArtists = getArtistsFromApi()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDb(newListArtists)
        artistCachedDataSource.saveArtistsToCache(newListArtists)
        return newListArtists
    }

    private suspend fun getArtistsFromApi(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            if (body != null) {
                artistList = body.people
            }

        } catch (exception: Exception) {
            artistList = emptyList()
        }
        return artistList

    }

    private suspend fun getArtistsFromDb(): List<Artist> {
        lateinit var artistList: List<Artist>
        artistList = try {
            artistLocalDataSource.getArtistsFromDb()
        } catch (exception: Exception) {
            emptyList()
        }
        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getArtistsFromApi()
            artistLocalDataSource.saveArtistsToDb(artistList)
        }
        return artistList

    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistList: List<Artist>
        artistList = try {
            artistCachedDataSource.getArtistsFromCache()
        } catch (exception: Exception) {
            emptyList()
        }
        if (artistList.isNotEmpty()) {
            return artistList
        } else {
            artistList = getArtistsFromDb()
            artistCachedDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }
}