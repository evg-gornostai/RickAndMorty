package com.gornostai.rickandmorty.ui.screens.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentEpisodesBinding
import com.gornostai.rickandmorty.ui.screens.episodeDetails.EpisodeDetailsFragment
import com.gornostai.rickandmorty.ui.screens.episodes.adapters.EpisodesAdapter
import com.gornostai.rickandmorty.utills.HasCustomTitle
import com.gornostai.rickandmorty.utills.Navigator

class EpisodesFragment : Fragment(), HasCustomTitle {

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
        vieModel.episodesList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        setupRecyclerView()
    }

    override fun getTitleRes(): Int = R.string.episodes_title

    private fun setupRecyclerView() {
        binding.rvEpisodes.adapter = adapter
        adapter.onEpisodeItemClickListener = {
            Navigator.setFragment(
                EpisodeDetailsFragment.newInstance(it.id),
                (requireActivity() as AppCompatActivity),
                true
            )
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = EpisodesFragment()
    }
}