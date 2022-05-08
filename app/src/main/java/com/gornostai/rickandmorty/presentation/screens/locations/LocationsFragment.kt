package com.gornostai.rickandmorty.presentation.screens.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentLocationsBinding
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.locationDetails.LocationDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.locations.adapters.LocationsAdapter

class LocationsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentLocationsBinding
    private lateinit var viewModel: LocationsViewModel

    private val adapter: LocationsAdapter by lazy { LocationsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LocationsViewModel::class.java]
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
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

    override fun getTitleRes(): Int = R.string.locations_title

    private fun setupRecyclerView() {
        binding.rvLocations.adapter = adapter
        adapter.onLocationItemClickListener = {
            navigator().setFragment(
                newFragment = LocationDetailsFragment.newInstance(it.id),
                addToBackStack = true
            )
        }
    }

    private fun setupData() {
        viewModel.locationsList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.tvEmptyMessage.visibility = View.VISIBLE
            } else {
                binding.tvEmptyMessage.visibility = View.GONE
            }
            adapter.setData(it)
        }
    }

    private fun setupLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeToRefresh.isRefreshing = it
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = LocationsFragment()
    }
}