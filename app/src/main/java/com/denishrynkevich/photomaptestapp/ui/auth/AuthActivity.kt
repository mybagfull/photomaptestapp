package com.denishrynkevich.photomaptestapp.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denishrynkevich.photomaptestapp.DaggerApp
import com.denishrynkevich.photomaptestapp.R
import com.denishrynkevich.photomaptestapp.databinding.ActivityAuthBinding
import com.denishrynkevich.photomaptestapp.ui.auth.login.LoginFragment
import com.denishrynkevich.photomaptestapp.ui.auth.registration.RegistrationFragment
import com.google.android.material.tabs.TabLayoutMediator

class AuthActivity : AppCompatActivity() {

    private val fragmentList = listOf(
        LoginFragment.newInstance(),
        RegistrationFragment.newInstance()
    )


    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as DaggerApp).appComponent.inject(this)


        val fragmentListTitle = listOf(
            getString(R.string.login),
            getString(R.string.register),
        )

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sectionsPagerAdapter = VpAdaprer(this, fragmentList)
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, pos ->
            tab.text = fragmentListTitle[pos]
        }.attach()
    }
}