package com.example.onlinebazar.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinebazar.fragments.categories.MainCategoryFragment
import com.example.onlinebazar.R
import com.example.onlinebazar.adapters.HomeViewpagerAdapter
import com.example.onlinebazar.databinding.FragmentHomeBinding
import com.example.onlinebazar.fragments.categories.AccessoryFragment
import com.example.onlinebazar.fragments.categories.ChairFragment
import com.example.onlinebazar.fragments.categories.CupboardFragment
import com.example.onlinebazar.fragments.categories.FurnitureFragment
import com.example.onlinebazar.fragments.categories.TableFragment
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment:Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewpagerHome.isUserInputEnabled = false
        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )
        val viewPager2Adapter =
            HomeViewpagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Statue"
                2 -> tab.text = "Singing Bowls"
                3 -> tab.text = "Thanka Painting"
                4 -> tab.text = "Beads Mala"
                5 -> tab.text = "Hemp Product"

            }
        }.attach()
    }
}
