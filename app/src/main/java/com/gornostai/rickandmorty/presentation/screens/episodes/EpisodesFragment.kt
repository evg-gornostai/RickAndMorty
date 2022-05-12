package com.gornostai.rickandmorty.presentation.screens.episodes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentEpisodesBinding
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.HasFilterButton
import com.gornostai.rickandmorty.presentation.contracts.HasSearchButton
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.episodeDetails.EpisodeDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.episodes.adapters.EpisodesAdapter
import com.gornostai.rickandmorty.presentation.screens.episodes.adapters.SpacesItemDecoration
import com.gornostai.rickandmorty.utils.App
import com.gornostai.rickandmorty.utils.ViewModelFactory
import javax.inject.Inject

class EpisodesFragment : Fragment(), HasCustomTitle, HasFilterButton, HasSearchButton {

    private lateinit var binding: FragmentEpisodesBinding
    private lateinit var viewModel: EpisodesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val adapter by lazy { EpisodesAdapter() }
    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[EpisodesViewModel::class.java]
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupData()
        setupLoading()
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    override fun getTitleRes(): Int = R.string.episodes_title

    override fun onFilterPressed() {

    }

    override fun onSearchPressed() {

    }

    private fun setupRecyclerView() {
        binding.rvEpisodes.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvEpisodes.addItemDecoration(SpacesItemDecoration(16f, requireContext()))
        binding.rvEpisodes.adapter = adapter
        adapter.onEpisodeItemClickListener = {
            navigator().setFragment(
                newFragment = EpisodeDetailsFragment.newInstance(it.id),
                addToBackStack = true
            )
        }
    }

    private fun setupData() {
        viewModel.episodesList.observe(viewLifecycleOwner) {
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

    companion object {

        @JvmStatic
        fun newInstance() = EpisodesFragment()
    }
}