package com.gornostai.rickandmorty.presentation.screens.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.ActivityMainBinding
import com.gornostai.rickandmorty.presentation.contracts.*
import com.gornostai.rickandmorty.presentation.screens.characters.CharactersFragment
import com.gornostai.rickandmorty.presentation.screens.episodes.EpisodesFragment
import com.gornostai.rickandmorty.presentation.screens.locations.LocationsFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    private val fragmentListener =
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                updateUi()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        if (savedInstanceState == null) {
            setFragment(CharactersFragment.newInstance())
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)

        setBottomNavListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setFragment(
        newFragment: Fragment,
        addToBackStack: Boolean,
        popBackStack: Boolean
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        if (popBackStack) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        transaction.replace(R.id.main_fragment_container, newFragment)
        transaction.commit()
    }

    private fun setBottomNavListener() {
        binding.mainBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_characters -> {
                    setFragment(
                        newFragment = CharactersFragment.newInstance(),
                        popBackStack = true
                    )
                }
                R.id.menu_episodes -> {
                    setFragment(
                        newFragment = EpisodesFragment.newInstance(),
                        popBackStack = true
                    )
                }
                R.id.menu_locations -> {
                    setFragment(
                        newFragment = LocationsFragment.newInstance(),
                        popBackStack = true
                    )
                }
            }
            true
        }
    }

    private fun updateUi() {
        val fragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)

        if (fragment is HasCustomTitle) {
            binding.tvTitle.text = getString(fragment.getTitleRes())
        } else {
            binding.tvTitle.text = getString(R.string.app_name)
        }

        if (fragment is HasBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        if (fragment is HasFilterButton) {
            binding.btnFilter.setOnClickListener { fragment.onFilterPressed() }
            binding.btnFilter.visibility = View.VISIBLE
        } else {
            binding.btnFilter.visibility = View.GONE
        }

        if (fragment is HasSearchButton) {
            binding.btnSearch.setOnClickListener { fragment.onSearchPressed() }
            binding.btnSearch.visibility = View.VISIBLE
        } else {
            binding.btnSearch.visibility = View.GONE
        }

    }

}