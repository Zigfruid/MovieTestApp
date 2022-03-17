package com.example.testmovieapp.ui.upcoming

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.testmovieapp.R
import com.example.testmovieapp.core.extentions.inflate
import com.example.testmovieapp.core.extentions.scope
import com.example.testmovieapp.data.model.Movie
import com.example.testmovieapp.databinding.ItemMovieBinding

class UpComingAdapter:RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>() {

    var models:List<Movie> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val itemView =parent.inflate(R.layout.item_movie)
        val binding = ItemMovieBinding.bind(itemView)
        return UpComingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size

    inner class UpComingViewHolder(private val binding: ItemMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(model: Movie) = binding.scope {
            binding.apply {
                tvTitle.text = model.title
                val glideUrl = GlideUrl("https://image.tmdb.org/t/p/original/${model.posterPath}")
                Glide.with(root)
                    .load(glideUrl)
                    .into(imageView)
            }
        }
    }
}