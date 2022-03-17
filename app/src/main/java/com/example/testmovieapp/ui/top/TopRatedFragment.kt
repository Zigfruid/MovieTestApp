package com.example.testmovieapp.ui.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.testmovieapp.R
import com.example.testmovieapp.databinding.FragmentTopRatedBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedFragment : Fragment() {

    private var _binding:FragmentTopRatedBinding? = null
    private val binding get()= _binding!!
    private val adapter:TopRatedAdapter by inject()
    private val viewModel:TopRatedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTopRated.adapter = adapter
        viewModel.getTopRatedMovies()
        setUpObservers()
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