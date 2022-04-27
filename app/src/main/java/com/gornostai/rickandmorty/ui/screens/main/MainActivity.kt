package com.gornostai.rickandmorty.ui.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gornostai.rickandmorty.ui.screens.characters.CharactersFragment
import com.gornostai.rickandmorty.ui.screens.episodes.EpisodesFragment
import com.gornostai.rickandmorty.ui.screens.locations.LocationsFragment
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.ActivityMainBinding
import com.gornostai.rickandmorty.utills.Navigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            Navigator.setFragment(CharactersFragment.newInstance(), this)
        }

        setBottomNavListener()
    }

    fun setBottomNavListener(){
        binding.mainBottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menu_characters -> {
                    Navigator.setFragment(CharactersFragment.newInstance(), this)
                }
                R.id.menu_episodes -> {
                    Navigator.setFragment(EpisodesFragment.newInstance(), this)
                }
                R.id.menu_locations -> {
                    Navigator.setFragment(LocationsFragment.newInstance(), this)
                }
            }
            true
        }
    }

}