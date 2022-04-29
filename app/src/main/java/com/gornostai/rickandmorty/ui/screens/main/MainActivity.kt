package com.gornostai.rickandmorty.ui.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gornostai.rickandmorty.ui.screens.characters.CharactersFragment
import com.gornostai.rickandmorty.ui.screens.episodes.EpisodesFragment
import com.gornostai.rickandmorty.ui.screens.locations.LocationsFragment
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.ActivityMainBinding
import com.gornostai.rickandmorty.utills.HasCustomTitle
import com.gornostai.rickandmorty.utills.Navigator

class MainActivity : AppCompatActivity() {

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
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            Navigator.setFragment(CharactersFragment.newInstance(), this)
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)

        setBottomNavListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private fun setBottomNavListener() {
        binding.mainBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_characters -> {
                    Navigator.setFragment(
                        newFragment = CharactersFragment.newInstance(),
                        activity = this,
                        popBackStack = true
                    )
                }
                R.id.menu_episodes -> {
                    Navigator.setFragment(
                        newFragment = EpisodesFragment.newInstance(),
                        activity = this,
                        popBackStack = true
                    )
                }
                R.id.menu_locations -> {
                    Navigator.setFragment(
                        newFragment = LocationsFragment.newInstance(),
                        activity = this,
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
            supportActionBar?.title = getString(fragment.getTitleRes())
        } else {
            supportActionBar?.title = getString(R.string.app_name)
        }

    }

}