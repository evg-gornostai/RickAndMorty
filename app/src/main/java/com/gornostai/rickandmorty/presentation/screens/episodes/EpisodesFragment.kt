package com.gornostai.rickandmorty.presentation.screens.episodes

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.DialogEpisodesFilterBinding
import com.gornostai.rickandmorty.databinding.FragmentEpisodesBinding
import com.gornostai.rickandmorty.domain.entities.EpisodeFilterEntity
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
    private lateinit var textWatcher: TextWatcher

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter by lazy { EpisodesAdapter() }
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
        setupTextWatcher()
        binding.swipeToRefresh.setOnRefreshListener {
            if (binding.inputLayoutEpisodesSearch.visibility == View.VISIBLE) {
                hideSearch()
            }
            viewModel.fetchData()
        }
    }

    override fun getTitleRes(): Int = R.string.episodes_title

    override fun onFilterPressed() {
        showFilterDialog(viewModel.filter)
    }

    override fun onSearchPressed() {
        if (binding.inputLayoutEpisodesSearch.visibility == View.GONE) {
            showSearch()
        } else {
            hideSearch()
            viewModel.fetchData()
        }
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

    private fun showFilterDialog(filter: EpisodeFilterEntity) {
        val binding = DialogEpisodesFilterBinding.inflate(layoutInflater)
        binding.edEpisodeName.setText(filter.name)
        binding.edEpisodeCode.setText(filter.code)
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    viewModel.getFilteredData(
                        EpisodeFilterEntity(
                            name = binding.edEpisodeName.text.toString(),
                            code = binding.edEpisodeCode.text.toString()
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
            .setTitle(getString(R.string.dialog_episodes_filter))
            .setPositiveButton(getString(R.string.apply), listener)
            .setNegativeButton(getString(R.string.cancel), listener)
            .setNeutralButton(getString(R.string.clear), listener)
            .create()
        dialog.show()
    }

    private fun setupTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.getFilteredData(EpisodeFilterEntity(name = query.toString()))
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }
    }

    private fun showSearch() {
        binding.inputLayoutEpisodesSearch.visibility = View.VISIBLE
        binding.edEpisodesSearch.addTextChangedListener(textWatcher)
    }

    private fun hideSearch() {
        binding.edEpisodesSearch.removeTextChangedListener(textWatcher)
        binding.edEpisodesSearch.setText("")
        binding.inputLayoutEpisodesSearch.visibility = View.GONE
    }

    companion object {

        @JvmStatic
        fun newInstance() = EpisodesFragment()
    }
}