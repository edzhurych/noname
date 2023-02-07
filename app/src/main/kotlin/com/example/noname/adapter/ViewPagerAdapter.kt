package com.example.noname.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noname.view.saved.SavedUsersFragment
import com.example.noname.view.users.AllUsersFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        Log.d("edz", "createFragment(). position - $position")
        return when (position) {
            0 -> AllUsersFragment()
            1 -> SavedUsersFragment()
            else -> AllUsersFragment()
        }
    }
}