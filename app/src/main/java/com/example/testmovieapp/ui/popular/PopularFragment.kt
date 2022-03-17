package com.example.testmovieapp.ui.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.testmovieapp.R
import com.example.testmovieapp.databinding.FragmentPopularBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private val adapter: PopularAdapter by inject()
    private val viewModel:PopularViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPopularBinding.bind(view)
        binding.rvPopular.adapter = adapter
        viewModel.getPopularMovies()
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