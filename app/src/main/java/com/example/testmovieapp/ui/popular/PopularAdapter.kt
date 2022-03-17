package com.example.testmovieapp.ui.popular

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapp.R
import com.example.testmovieapp.core.extentions.inflate
import com.example.testmovieapp.data.model.Movie
import com.example.testmovieapp.databinding.ItemMovieBinding

class PopularAdapter:RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    var models:List<Movie> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PopularViewHolder(private val binding:ItemMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(movie: Movie) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.PopularViewHolder {
        val itemView =parent.inflate(R.layout.item_movie)
        val binding = ItemMovieBinding.bind(itemView)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size
}