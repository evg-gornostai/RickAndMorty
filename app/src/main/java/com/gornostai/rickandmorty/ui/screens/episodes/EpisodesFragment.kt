package com.gornostai.rickandmorty.ui.screens.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gornostai.rickandmorty.R

class EpisodesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = EpisodesFragment()
    }
}