package com.gornostai.rickandmorty.ui.screens.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.databinding.FragmentEpisodesBinding
import com.gornostai.rickandmorty.ui.screens.episodes.adapters.EpisodesAdapter

class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private lateinit var vieModel: EpisodesViewModel

    val adapter = EpisodesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vieModel = ViewModelProvider(this)[EpisodesViewModel::class.java]
        vieModel.episodesList.observe(viewLifecycleOwner){
            adapter.setData(it)
        }
        binding.rvEpisodes.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() = EpisodesFragment()
    }
}