package com.example.testmovieapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testmovieapp.core.MarginItemDecoration
import com.example.testmovieapp.core.extentions.dp
import com.example.testmovieapp.databinding.FragmentPopularBinding
import com.example.testmovieapp.ui.main.MainFragmentDirections
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {

    private var _binding:FragmentPopularBinding? = null
    private val binding get() = _binding!!
    private val adapter: PopularAdapter by inject()
    private val viewModel:PopularViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPopular.adapter = adapter
        binding.rvPopular.addItemDecoration(MarginItemDecoration(4.dp))
        viewModel.getPopularMovies()
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