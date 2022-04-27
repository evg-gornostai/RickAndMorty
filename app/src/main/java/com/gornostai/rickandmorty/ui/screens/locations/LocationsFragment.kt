package com.gornostai.rickandmorty.ui.screens.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gornostai.rickandmorty.databinding.FragmentLocationsBinding
import com.gornostai.rickandmorty.ui.screens.locations.adapters.LocationsAdapter

class LocationsFragment : Fragment() {

    private lateinit var binding: FragmentLocationsBinding

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
        binding.rvLocations.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() = LocationsFragment()
    }
}