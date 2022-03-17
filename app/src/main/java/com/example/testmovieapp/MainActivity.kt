package com.example.testmovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.testmovieapp.databinding.ActivityMainBinding
import com.example.testmovieapp.ui.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val adapter  = ViewPagerAdapter(supportFragmentManager, lifecycle)
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

