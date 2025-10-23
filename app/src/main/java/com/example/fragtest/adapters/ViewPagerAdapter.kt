package com.example.fragtest.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragtest.fragments.GroupFragment
import com.example.fragtest.fragments.MusicFragment

class ViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity)
{
    /**/
    private val tabTitles = listOf("Reproductor","Grupos")

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> MusicFragment()
            1 -> GroupFragment()
            else -> throw IllegalStateException("Invalid position:$position")
        }
    }

    override fun getItemCount(): Int = tabTitles.size

}