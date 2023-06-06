package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository {
    private val movies = mutableListOf<Movie>()

    init {
        movies.add(Movie(1, "overview1", "posterpath1", "releasedate1", "title"))
        movies.add(
            Movie(2, "overview2", "posterpath2", "releasedate2", "title2")
        )
        movies.add(
            Movie(3, "overview3", "posterpath3", "releasedate3", "title3")
        )

    }

    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        movies.clear()
        movies.add(Movie(1, "overview1", "posterpath1", "releasedate1", "title"))
        movies.add(
            Movie(2, "overview2", "posterpath2", "releasedate2", "title2")
        )
        movies.add(
            Movie(3, "overview3", "posterpath3", "releasedate3", "title3")
        )
        movies.add(
            Movie(4, "overview4", "posterpath4", "releasedate4", "title4")
        )
        return movies
    }
}