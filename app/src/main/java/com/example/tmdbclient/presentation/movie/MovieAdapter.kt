package com.example.tmdbclient.presentation.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.databinding.ListItemBinding

class MovieAdapter() : RecyclerView.Adapter<MyViewHolder>() {
    private val movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemBinding>(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        Log.i("MYTAG", "list size ${movieList.size}")
        return movieList.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.titleTextView.text = movie.title
        binding.descriptionTextView.text = movie.overview
        val posterURL: String = "https://image.tmdb.org/t/p/w500" + movie.posterPath.toString()
        Glide.with(binding.imageView).load(posterURL).into(binding.imageView)
    }

}