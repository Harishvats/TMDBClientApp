package com.example.tmdbclient.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityHomeBinding
import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.movie.MovieActivity
import com.example.tmdbclient.presentation.tvshow.TvShowActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        binding.movieButton.setOnClickListener {
            startActivity(Intent(this, MovieActivity::class.java))

        }
        binding.artistsButton.setOnClickListener {
            startActivity(Intent(this, ArtistActivity::class.java))


        }
        binding.tvButton.setOnClickListener {
            startActivity(Intent(this, TvShowActivity::class.java))

        }


    }


}
