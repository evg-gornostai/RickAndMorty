package com.gornostai.rickandmorty.presentation.screens.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.gornostai.rickandmorty.presentation.contracts.HasBackButton
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.characterDetails.adapters.EpisodesAdapter
import com.gornostai.rickandmorty.presentation.screens.characterDetails.adapters.SpacesItemDecoration
import com.gornostai.rickandmorty.presentation.screens.episodeDetails.EpisodeDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.locationDetails.LocationDetailsFragment

class CharacterDetailsFragment : Fragment(), HasCustomTitle, HasBackButton {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private lateinit var viewModel: CharacterDetailsViewModel

    private val adapter by lazy { EpisodesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterDetailsViewModel::class.java]
        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
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

    override fun getTitleRes(): Int = R.string.character_details_title

    private fun loadData() {
        val id = arguments.let {
            it?.getInt(ARG_CHARACTER_ITEM_ID)
        }
        viewModel.loadData(id ?: throw RuntimeException("id is null"))
    }

    private fun setupData() {
        viewModel.characterItem.observe(viewLifecycleOwner) { character ->
            with(binding) {
                Glide
                    .with(this@CharacterDetailsFragment)
                    .load(character.image)
                    .placeholder(getCircularProgressDrawable())
                    .into(ivCharacterImage)
                tvCharacterGender.text = getString(R.string.gender_character_gender)
                    .replace("[CHARACTER_GENDER]", character.gender)
                tvCharacterName.text = getString(R.string.name_character_name)
                    .replace("[CHARACTER_NAME]", character.name)
                tvCharacterSpecies.text = getString(R.string.species_character_species)
                    .replace("[CHARACTER_SPECIES]", character.species)
                tvCharacterStatus.text = getString(R.string.status_character_status)
                    .replace("[CHARACTER_STATUS]", character.status)
                tvCharacterType.text = getString(R.string.type_character_type)
                    .replace("[CHARACTER_TYPE]", character.type)
                includedOriginLocation.root.setOnClickListener {
                    if (character.originId.isNotEmpty()) {
                        navigator().setFragment(
                            newFragment = LocationDetailsFragment.newInstance(character.originId.toInt()),
                            addToBackStack = true
                        )
                    }
                }
                includedLastKnownLocation.root.setOnClickListener {
                    if (character.locationId.isNotEmpty()) {
                        navigator().setFragment(
                            newFragment = LocationDetailsFragment.newInstance(character.locationId.toInt()),
                            addToBackStack = true
                        )
                    }
                }
            }

        }

        viewModel.originLocation.observe(viewLifecycleOwner) {
            with(binding) {
                includedOriginLocation.root.visibility = View.VISIBLE
                includedOriginLocation.tvLocationDimension.text = it.dimension
                includedOriginLocation.tvLocationName.text = it.name
                includedOriginLocation.tvLocationType.text = it.type
            }
        }

        viewModel.lastLocation.observe(viewLifecycleOwner) {
            with(binding) {
                includedLastKnownLocation.root.visibility = View.VISIBLE
                includedLastKnownLocation.tvLocationName.text = it.name
                includedLastKnownLocation.tvLocationDimension.text = it.dimension
                includedLastKnownLocation.tvLocationType.text = it.type
            }
        }

        viewModel.episodesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeToRefresh.isRefreshing = it
        }
    }

    private fun setupRecyclerView() {
        binding.rvEpisodes.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvEpisodes.addItemDecoration(SpacesItemDecoration(16f, requireContext()))
        binding.rvEpisodes.adapter = adapter
        adapter.onEpisodeItemClickListener = {
            navigator().setFragment(
                newFragment = EpisodeDetailsFragment.newInstance(it.id),
                addToBackStack = true
            )
        }
    }

    private fun getCircularProgressDrawable(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    companion object {

        const val ARG_CHARACTER_ITEM_ID = "ARG_CHARACTER_ITEM_ID"

        fun newInstance(characterItemId: Int) =
            CharacterDetailsFragment().apply {
                arguments = bundleOf(ARG_CHARACTER_ITEM_ID to characterItemId)
            }
    }

}