package com.example.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDao: MovieDao
    private lateinit var database: TMDBDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()
        movieDao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun test_saveMovie() {
        runBlocking {
//            val moviesList = listOf<Movie>()
            val movies = listOf(
                Movie(1, "overview1", "posterpath1", "releasedate1", "title"),
                Movie(2, "overview2", "posterpath2", "releasedate2", "title2"),
                Movie(3, "overview3", "posterpath3", "releasedate3", "title3"),
                Movie(4, "overview4", "posterpath4", "releasedate4", "title4")
            )
            movieDao.saveMovies(movies)

            val allMovies = movieDao.getAllMovies()

            Assert.assertEquals(movies, allMovies)
//            Assert.assertEquals(moviesList, allMovies)

        }

    }

    @Test
    fun test_deleteMovies() {
        runBlocking {
            val movies = listOf(
                Movie(1, "overview1", "posterpath1", "releasedate1", "title"),
                Movie(2, "overview2", "posterpath2", "releasedate2", "title2"),
                Movie(3, "overview3", "posterpath3", "releasedate3", "title3"),
                Movie(4, "overview4", "posterpath4", "releasedate4", "title4")
            )
            movieDao.saveMovies(movies)
            movieDao.deleteAllMovies()
            val allMovies = movieDao.getAllMovies()
            Assert.assertEquals(emptyList<Movie>(), allMovies)

        }
    }
}