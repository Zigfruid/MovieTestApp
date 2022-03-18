package com.example.testmovieapp.ui.detail.actor

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.testmovieapp.R
import com.example.testmovieapp.core.extentions.inflate
import com.example.testmovieapp.core.extentions.onClick
import com.example.testmovieapp.core.extentions.scope
import com.example.testmovieapp.data.model.Actor
import com.example.testmovieapp.data.model.Movie
import com.example.testmovieapp.databinding.ItemMoviePosterBinding

class DetailMovieAdapter:RecyclerView.Adapter<DetailMovieAdapter.ViewHolder>() {

    var models: MutableList<Movie> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class ViewHolder(private val binding:ItemMoviePosterBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(model: Movie) = binding.scope {
            tvName.text = model.originalTitle
            val glideUrl = GlideUrl("https://image.tmdb.org/t/p/original/${model.posterPath}")
            Glide.with(root)
                .load(glideUrl)
                .placeholder(R.drawable.no_image)
                .into(ivMovie)

            binding.root.onClick {
                onClickItem.invoke(model)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =parent.inflate(R.layout.item_movie_poster)
        val binding = ItemMoviePosterBinding.bind(itemView)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size

    private var onClickItem:(movie:Movie) -> Unit = {}
    fun setOnClickItem(onClickItem:(movie:Movie) -> Unit){
        this.onClickItem=onClickItem
    }
}