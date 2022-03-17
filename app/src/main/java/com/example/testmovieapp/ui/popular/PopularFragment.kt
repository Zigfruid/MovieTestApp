package com.example.testmovieapp.ui.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testmovieapp.R
import com.example.testmovieapp.databinding.FragmentPopularBinding
import org.koin.android.ext.android.inject

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private val adapter: PopularAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPopularBinding.bind(view)
        binding.rvPopular.adapter = adapter

    }
}