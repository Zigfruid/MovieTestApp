package com.example.testmovieapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testmovieapp.ui.popular.PopularFragment
import com.example.testmovieapp.ui.top.TopRatedFragment
import com.example.testmovieapp.ui.upcoming.UpcomingFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                PopularFragment()
            }
            1->{
                TopRatedFragment()
            }
            else->{
                UpcomingFragment()
            }
        }
    }
}