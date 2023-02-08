package com.example.noname.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.noname.R
import com.example.noname.adapter.ViewPagerAdapter
import com.example.noname.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var binding: ActivityHomeBinding
    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ViewPagerAdapter(this)
        viewPager = binding.viewPager
        viewPager.adapter = adapter

        tabLayoutMediator = TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.all_users)
                1 -> tab.text = getString(R.string.saved_users)
            }
        }
        tabLayoutMediator.attach()
    }
}
