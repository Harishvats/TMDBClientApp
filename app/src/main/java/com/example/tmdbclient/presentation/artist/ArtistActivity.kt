package com.example.tmdbclient.presentation.artist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityArtistBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtistActivity : AppCompatActivity() {

    @Inject
    lateinit var artistVMFactory: ArtistVMFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    private lateinit var binding: ActivityArtistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        artistViewModel = ViewModelProvider(this, artistVMFactory)[ArtistViewModel::class.java]
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateArtists()
                true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }

    private fun initRecyclerView() {
        adapter = ArtistAdapter()
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.artistRecyclerView.adapter = adapter
        getArtists()
    }

    private fun getArtists() {
        val responseData = artistViewModel.getArtists()
        responseData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Data Available!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun updateArtists() {
        val responseData = artistViewModel.updateArtists()
        responseData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Data Available!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}