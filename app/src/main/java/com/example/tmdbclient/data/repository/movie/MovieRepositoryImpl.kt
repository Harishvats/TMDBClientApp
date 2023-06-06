package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCachedDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCachedDataSource: MovieCachedDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {

        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {

        val newListMovies = getMoviesFromApi()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDb(newListMovies)
        movieCachedDataSource.saveMoviesToCache(newListMovies)
        return newListMovies
    }

    private suspend fun getMoviesFromApi(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }

        } catch (exception: Exception) {
            movieList = emptyList()
        }
        return movieList

    }

    private suspend fun getMoviesFromDb(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDb()
        } catch (exception: Exception) {
            movieList = emptyList()
        }
        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromApi()
            movieLocalDataSource.saveMoviesToDb(movieList)
        }
        return movieList

    }

    private suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCachedDataSource.getMoviesFromCache()
        } catch (exception: Exception) {
            movieList = emptyList()
        }
        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromDb()
            movieCachedDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}