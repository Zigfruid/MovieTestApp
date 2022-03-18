package com.example.testmovieapp.ui.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.testmovieapp.R
import com.example.testmovieapp.databinding.FragmentUpcomingBinding
import com.example.testmovieapp.ui.main.MainFragmentDirections
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private var _binding:FragmentUpcomingBinding? = null
    private val binding get() = _binding!!
    private val adapter:UpComingAdapter by inject()
    private val viewModel: UpComingViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUpComing.adapter = adapter
        viewModel.getUpComingMovies()
        setUpObservers()

        adapter.setOnClickItem {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val gsonString = gsonPretty.toJson(it)
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(gsonString))
        }
    }
    private fun setUpObservers(){
        viewModel.success.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                adapter.models = it.results
            } else {
                Toast.makeText(requireContext(), "empty!", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}