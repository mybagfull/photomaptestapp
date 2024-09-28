package com.denishrynkevich.photomaptestapp.ui.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdaprer(fa: FragmentActivity, private val fragmentList: List<Fragment>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}