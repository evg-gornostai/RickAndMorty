package com.gornostai.rickandmorty.ui.screens.locationDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.databinding.FragmentLocationDetailsBinding
import com.gornostai.rickandmorty.ui.screens.episodeDetails.EpisodeDetailsFragment

class LocationDetailsFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailsBinding

    private lateinit var viewModel: LocationDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LocationDetailsViewModel::class.java]
        initView()
    }

    private fun initView(){
        val id = arguments.let {
            it?.getInt(ARG_LOCATION_ITEM_ID)
        }
        viewModel.getLocationItem(id ?: throw RuntimeException("id is null"))
        viewModel.locationItem.observe(viewLifecycleOwner) {
            binding.tvTest.text = it.toString()
        }
    }

    companion object {

        const val ARG_LOCATION_ITEM_ID = "ARG_LOCATION_ITEM_ID"

        fun newInstance(locationItemId: Int) =
            LocationDetailsFragment().apply {
                arguments = bundleOf(ARG_LOCATION_ITEM_ID to locationItemId)
            }
    }

}