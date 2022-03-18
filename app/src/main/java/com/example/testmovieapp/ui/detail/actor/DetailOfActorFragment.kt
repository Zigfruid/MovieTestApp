package com.example.testmovieapp.ui.detail.actor

import android.os.Bundle
import android.util.Log
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
import com.example.testmovieapp.databinding.FragmentDetailActorBinding
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailOfActorFragment:Fragment() {

    private var _binding:FragmentDetailActorBinding?= null
    private val binding get() = _binding!!
    private val viewModel:BioViewModel by viewModel()
    private val safeArgs:DetailOfActorFragmentArgs by navArgs()
    private val adapter:DetailMovieAdapter by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailActorBinding.inflate(layoutInflater,container, false)
        return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actorId = safeArgs.actorId
        viewModel.getBioCredits(actorId)
        viewModel.getMovieCredits(actorId)
        setUpObservers()
        binding.btnBack.onClick {
            findNavController().popBackStack()
        }
        binding.btnHome.onClick {
            findNavController().navigate(DetailOfActorFragmentDirections.actionDetailOfActorFragmentToMainFragment())
        }

        adapter.setOnClickItem {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val gsonString = gsonPretty.toJson(it)
            findNavController().navigate(DetailOfActorFragmentDirections.actionDetailOfActorFragmentToDetailFragment(gsonString))
        }

    }
    private fun setUpObservers(){
        viewModel.success.observe(viewLifecycleOwner) {
            binding.apply {
                recyclerView.adapter = adapter
                val glideUrl = GlideUrl("https://image.tmdb.org/t/p/original/${it.profilePath}")
                Glide.with(root)
                    .load(glideUrl)
                    .into(ivActor)
                tvOriginalTitle.text=it.name
                tvBio.text=it.biography
                tvRank.text=it.popularity.toString()
            }
        }
        viewModel.successMovie.observe(viewLifecycleOwner){
            adapter.models = it.cast.toMutableList()
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}