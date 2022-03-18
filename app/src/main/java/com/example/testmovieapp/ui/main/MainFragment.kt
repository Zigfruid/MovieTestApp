package com.example.testmovieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testmovieapp.databinding.FragmentMainBinding
import com.example.testmovieapp.ui.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter  = ViewPagerAdapter(requireActivity())
        binding.viewPager2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){tab, position->
            when(position){
                0->{
                    tab.text = "Popular"
                }
                1->{
                    tab.text = "Top Rated"
                }
                2->{
                    tab.text = "Upcoming"
                }
            }
        }.attach()
    }
}