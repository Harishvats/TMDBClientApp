package com.example.tmdbclient.presentation.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var movieVMFactory: MovieVMFactory

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var adapter: MovieAdapter

    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        movieViewModel = ViewModelProvider(this, movieVMFactory)[MovieViewModel::class.java]
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateMovies()
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }

    private fun initRecyclerView() {
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.movieRecyclerView.adapter = adapter
        binding.movieProgressBar.visibility = View.VISIBLE
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        val responseData = movieViewModel.getMovies()
        responseData.observe(this) {
            binding.movieProgressBar.visibility = View.INVISIBLE

            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Data Available!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseData = movieViewModel.updateMovies()
        responseData.observe(this) {
            binding.movieProgressBar.visibility = View.INVISIBLE
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Data Available!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}