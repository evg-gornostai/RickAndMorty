package com.gornostai.rickandmorty.presentation.screens.locations

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.DialogLocationsFilterBinding
import com.gornostai.rickandmorty.databinding.FragmentLocationsBinding
import com.gornostai.rickandmorty.domain.entities.LocationFilterEntity
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.HasFilterButton
import com.gornostai.rickandmorty.presentation.contracts.HasSearchButton
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.locationDetails.LocationDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.locations.adapters.LocationsAdapter
import com.gornostai.rickandmorty.presentation.screens.locations.adapters.SpacesItemDecoration
import com.gornostai.rickandmorty.utils.App
import com.gornostai.rickandmorty.utils.ViewModelFactory
import javax.inject.Inject

class LocationsFragment : Fragment(), HasCustomTitle, HasFilterButton, HasSearchButton {

    private lateinit var binding: FragmentLocationsBinding
    private lateinit var viewModel: LocationsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter by lazy { LocationsAdapter() }
    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LocationsViewModel::class.java]
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
            viewModel.fetchData()
        }
    }

    override fun getTitleRes(): Int = R.string.locations_title

    override fun onFilterPressed() {
        showFilterDialog(viewModel.filter)
    }

    override fun onSearchPressed() {

    }

    private fun setupRecyclerView() {
        binding.rvLocations.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvLocations.addItemDecoration(SpacesItemDecoration(16f, requireContext()))
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
            adapter.submitList(it)
        }
    }

    private fun setupLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeToRefresh.isRefreshing = it
        }
    }

    private fun showFilterDialog(filter: LocationFilterEntity) {
        val binding = DialogLocationsFilterBinding.inflate(layoutInflater)
        binding.edLocationName.setText(filter.name)
        binding.edLocationDimension.setText(filter.dimension)
        binding.edLocationType.setText(filter.type)
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    viewModel.getFilteredData(
                        LocationFilterEntity(
                            name = binding.edLocationName.text.toString(),
                            dimension = binding.edLocationDimension.text.toString(),
                            type = binding.edLocationType.text.toString()
                        )
                    )
                }
                DialogInterface.BUTTON_NEGATIVE -> {}
                DialogInterface.BUTTON_NEUTRAL -> {
                    viewModel.fetchData()
                }
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setView(binding.root)
            .setTitle(getString(R.string.dialog_locations_filter))
            .setPositiveButton(getString(R.string.apply), listener)
            .setNegativeButton(getString(R.string.cancel), listener)
            .setNeutralButton(getString(R.string.clear), listener)
            .create()
        dialog.show()
    }

    companion object {

        @JvmStatic
        fun newInstance() = LocationsFragment()
    }
}