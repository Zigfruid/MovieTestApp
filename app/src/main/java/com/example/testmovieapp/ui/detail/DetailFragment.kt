package com.example.testmovieapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.testmovieapp.core.extentions.onClick
import com.example.testmovieapp.data.model.Movie
import com.example.testmovieapp.databinding.FragmentDetailBinding
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class DetailFragment: Fragment() {

    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: DetailFragmentArgs by navArgs()
    private val viewModel:DetailViewModel by inject()
    private val adapter:DetailAdapter by inject()
    private lateinit var movie: Movie
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson =Gson()
        movie =gson.fromJson(safeArgs.movie, Movie::class.java)

        viewModel.getMovieCredits(movie.id)
        binding.apply {
            recyclerView.adapter = adapter
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
    }

    private fun setUpObservers(){
        viewModel.success.observe(viewLifecycleOwner) {
            adapter.models = it.cast.toMutableList()
        }
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}