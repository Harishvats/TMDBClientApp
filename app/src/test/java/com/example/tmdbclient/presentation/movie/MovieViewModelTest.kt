package com.example.tmdbclient.presentation.movie


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.FakeMovieRepository
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMovieUseCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        movieViewModel = MovieViewModel(getMovieUseCase, updateMoviesUseCase)
    }


    @Test
    fun testGetMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "overview1", "posterpath1", "releasedate1", "title"))
        movies.add(
            Movie(2, "overview2", "posterpath2", "releasedate2", "title2")
        )
        movies.add(
            Movie(3, "overview3", "posterpath3", "releasedate3", "title3")
        )


        val currentList = movieViewModel.getMovies().getOrAwaitValue()

        Truth.assertThat(currentList).isEqualTo(movies)
    }

    //    @Test
    fun testUpdateMovies() {
        val movies = mutableListOf<Movie>()
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

        val currentList = movieViewModel.updateMovies().getOrAwaitValue()

        Truth.assertThat(currentList).isEqualTo(movies)

    }

}