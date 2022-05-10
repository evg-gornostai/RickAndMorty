package com.gornostai.rickandmorty.presentation.screens.episodeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import com.gornostai.rickandmorty.presentation.contracts.HasBackButton
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.characterDetails.CharacterDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.episodeDetails.adapters.CharactersAdapter
import com.gornostai.rickandmorty.presentation.screens.episodeDetails.adapters.SpacesItemDecoration

class EpisodeDetailsFragment : Fragment(), HasCustomTitle, HasBackButton {

    private lateinit var binding: FragmentEpisodeDetailsBinding
    private lateinit var viewModel: EpisodeDetailsViewModel

    private val adapter by lazy { CharactersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EpisodeDetailsViewModel::class.java]
        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLoading()
        setupData()

        binding.swipeToRefresh.setOnRefreshListener {
            loadData()
        }
    }

    override fun getTitleRes(): Int = R.string.episode_details_title

    private fun loadData() {
        val id = arguments.let {
            it?.getInt(ARG_EPISODE_ITEM_ID)
        }
        viewModel.loadData(id ?: throw RuntimeException("id is null"))
    }

    private fun setupData() {
        viewModel.episodeItem.observe(viewLifecycleOwner) { episode ->
            with(binding) {
                tvEpisodeName.text = episode.name
                tvEpisode.text = getString(R.string.episode_episode)
                    .replace("[EPISODE_EPISODE]", episode.episode)
                tvEpisodeAirDate.text = getString(R.string.episode_air_date_episode)
                    .replace("[EPISODE_AIR_DATE]", episode.air_date)
            }
        }

        viewModel.charactersList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.tvEmptyMessage.visibility = View.VISIBLE
            } else {
                binding.tvEmptyMessage.visibility = View.GONE
            }
            adapter.submitList(it)
        }
    }

    private fun setupLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeToRefresh.isRefreshing = it
        }
    }

    private fun setupRecyclerView() {
        binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCharacters.addItemDecoration(SpacesItemDecoration(16f, requireContext()))
        binding.rvCharacters.adapter = adapter
        adapter.onCharacterItemClickListener = {
            navigator().setFragment(
                newFragment = CharacterDetailsFragment.newInstance(it.id),
                addToBackStack = true
            )
        }
    }

    companion object {

        const val ARG_EPISODE_ITEM_ID = "ARG_EPISODE_ITEM_ID"

        fun newInstance(episodeItemId: Int) =
            EpisodeDetailsFragment().apply {
                arguments = bundleOf(ARG_EPISODE_ITEM_ID to episodeItemId)
            }
    }

}