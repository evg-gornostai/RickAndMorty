package com.gornostai.rickandmorty.ui.screens.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentCharactersBinding
import com.gornostai.rickandmorty.ui.screens.characterDetails.CharacterDetailsFragment
import com.gornostai.rickandmorty.ui.screens.characters.adapters.CharactersAdapter
import com.gornostai.rickandmorty.utills.HasCustomTitle
import com.gornostai.rickandmorty.utills.Navigator

class CharactersFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var viewModel: CharactersViewModel

    val adapter: CharactersAdapter by lazy { CharactersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CharactersViewModel::class.java]
        setupRecyclerView()
        setupData()
        setupLoading()
        viewModel.fetchData()
    }

    override fun getTitleRes(): Int = R.string.characters_title

    private fun setupRecyclerView() {
        binding.rvCharacters.adapter = adapter
        adapter.onCharacterItemClickListener = {
            Navigator.setFragment(
                CharacterDetailsFragment.newInstance(it.id),
                (requireActivity() as AppCompatActivity),
                true
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
            adapter.setData(it)
        }
    }

    private fun setupLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = CharactersFragment()
    }

}