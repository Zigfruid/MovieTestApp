package com.example.testmovieapp.ui.detail.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.testmovieapp.core.extentions.onClick
import com.example.testmovieapp.data.model.Movie
import com.example.testmovieapp.databinding.FragmentDetailMovieBinding
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class DetailMovieFragment: Fragment() {

    private var _binding:FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val safeArgs:DetailMovieFragmentArgs by navArgs()
    private val movieViewModel: DetailMovieViewModel by inject()
    private val actorAdapter: DetailActorAdapter by inject()
    private lateinit var movie: Movie
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson =Gson()
        movie =gson.fromJson(safeArgs.movie, Movie::class.java)

        movieViewModel.getMovieCredits(movie.id)
        binding.apply {
            recyclerView.adapter = actorAdapter
            btnBack.onClick {
                findNavController().popBackStack()
            }
            val glideUrl = GlideUrl("https://image.tmdb.org/t/p/original/${movie.posterPath}")
            Glide.with(root)
                .load(glideUrl)
                .into(ivMovie)
            tvOriginalTitle.text=movie.originalTitle
            tvOverview.text=movie.overview
            tvRank.text=movie.voteAverage.toString()
        }
        setUpObservers()

        actorAdapter.setOnClickItem {
            findNavController().navigate(DetailMovieFragmentDirections.actionDetailFragmentToDetailOfActorFragment(it.id))
        }
    }

    private fun setUpObservers(){
        movieViewModel.success.observe(viewLifecycleOwner) {
            actorAdapter.models = it.cast.toMutableList()
        }
        movieViewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}