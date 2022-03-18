package com.example.testmovieapp.ui.detail.movie

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
import com.example.testmovieapp.databinding.ItemActorBinding

class DetailActorAdapter:RecyclerView.Adapter<DetailActorAdapter.DetailViewHolder>() {
    var models: MutableList<Actor> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val itemView =parent.inflate(R.layout.item_actor)
        val binding = ItemActorBinding.bind(itemView)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size

    inner class DetailViewHolder(private val binding:ItemActorBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(model: Actor) = binding.scope {
            tvName.text = model.name
            val glideUrl = GlideUrl("https://image.tmdb.org/t/p/original/${model.profilePath}")
            Glide.with(root)
                .load(glideUrl)
                .placeholder(R.drawable.no_icon)
                .into(ivActor)

            binding.root.onClick {
                onClickItem.invoke(model)
            }
        }
    }

    private var onClickItem:(actor: Actor) -> Unit = {}
    fun setOnClickItem(onClickItem:(actor: Actor) -> Unit){
        this.onClickItem=onClickItem
    }
}