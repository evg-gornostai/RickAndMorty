package com.gornostai.rickandmorty.presentation.screens.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentCharactersBinding
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.HasFilterButton
import com.gornostai.rickandmorty.presentation.contracts.HasSearchButton
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.characterDetails.CharacterDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.characters.adapters.CharactersAdapter
import com.gornostai.rickandmorty.presentation.screens.characters.adapters.SpacesItemDecoration

class CharactersFragment : Fragment(), HasCustomTitle, HasFilterButton, HasSearchButton {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var viewModel: CharactersViewModel

    val adapter: CharactersAdapter by lazy { CharactersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharactersViewModel::class.java]
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
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

    override fun getTitleRes(): Int = R.string.characters_title

    override fun onFilterPressed() {

    }

    override fun onSearchPressed() {

    }

    private fun setupRecyclerView() {
        binding.rvCharacters.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvCharacters.addItemDecoration(SpacesItemDecoration(50))
        binding.rvCharacters.adapter = adapter
        adapter.onCharacterItemClickListener = {
            navigator().setFragment(
                newFragment = CharacterDetailsFragment.newInstance(it.id),
                addToBackStack = true
            )
        }
    }

    private fun setupData() {
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

    companion object {

        @JvmStatic
        fun newInstance() = CharactersFragment()
    }

}