package com.gornostai.rickandmorty.presentation.screens.locationDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentLocationDetailsBinding
import com.gornostai.rickandmorty.presentation.contracts.HasBackButton
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.characterDetails.CharacterDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.locationDetails.adapters.CharactersAdapter
import com.gornostai.rickandmorty.presentation.screens.locationDetails.adapters.SpacesItemDecoration
import com.gornostai.rickandmorty.utils.App
import com.gornostai.rickandmorty.utils.ViewModelFactory
import javax.inject.Inject

class LocationDetailsFragment : Fragment(), HasCustomTitle, HasBackButton {

    private lateinit var binding: FragmentLocationDetailsBinding
    private lateinit var viewModel: LocationDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter by lazy { CharactersAdapter() }
    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LocationDetailsViewModel::class.java]
        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLoading()
        setupErrorListener()
        setupData()

        binding.swipeToRefresh.setOnRefreshListener {
            loadData()
        }
    }

    override fun getTitleRes(): Int = R.string.location_details_title

    private fun loadData() {
        val id = arguments.let {
            it?.getInt(ARG_LOCATION_ITEM_ID)
        }
        viewModel.loadData(id ?: throw RuntimeException("id is null"))
    }

    private fun setupData() {
        viewModel.locationItem.observe(viewLifecycleOwner) { location ->
            with(binding) {
                tvLocationName.text = location.name
                tvLocationType.text =
                    getString(R.string.type_location_type).replace("[LOCATION_TYPE]", location.type)
                tvLocationDimension.text =
                    getString(R.string.dimension_location_dimension).replace(
                        "[LOCATION_DIMENSION]",
                        location.dimension
                    )
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

    private fun setupErrorListener() {
        viewModel.showErrorMessage.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
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

        const val ARG_LOCATION_ITEM_ID = "ARG_LOCATION_ITEM_ID"

        fun newInstance(locationItemId: Int) =
            LocationDetailsFragment().apply {
                arguments = bundleOf(ARG_LOCATION_ITEM_ID to locationItemId)
            }
    }

}