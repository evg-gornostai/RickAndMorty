package com.gornostai.rickandmorty.ui.screens.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentLocationsBinding
import com.gornostai.rickandmorty.ui.screens.locationDetails.LocationDetailsFragment
import com.gornostai.rickandmorty.ui.screens.locations.adapters.LocationsAdapter
import com.gornostai.rickandmorty.utills.HasCustomTitle
import com.gornostai.rickandmorty.utills.Navigator

class LocationsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentLocationsBinding
    private lateinit var viewModel: LocationsViewModel

    private val adapter = LocationsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LocationsViewModel::class.java]
        viewModel.locationsList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        setupRecyclerView()
    }

    override fun getTitleRes(): Int = R.string.locations_title

    private fun setupRecyclerView() {
        binding.rvLocations.adapter = adapter
        adapter.onLocationItemClickListener = {
            Navigator.setFragment(
                LocationDetailsFragment.newInstance(it.id),
                (requireActivity() as AppCompatActivity),
                true
            )
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = LocationsFragment()
    }
}