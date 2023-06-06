package com.example.tmdbclient.presentation.tvshow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityTvShowBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvShowActivity : AppCompatActivity() {

    @Inject
    lateinit var tvShowVMFactory: TvShowVMFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var binding: ActivityTvShowBinding
    private lateinit var adapter: TvShowAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
        tvShowViewModel = ViewModelProvider(this, tvShowVMFactory)[TvShowViewModel::class.java]
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateTvShows()
                true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }

    private fun initRecyclerView() {
        adapter = TvShowAdapter()
        binding.tvShowRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tvShowRecyclerView.adapter = adapter
        getTvShows()
    }

    private fun getTvShows() {
        val responseData = tvShowViewModel.getTvShows()
        responseData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Data Available!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun updateTvShows() {
        val responseData = tvShowViewModel.updateTvShows()
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