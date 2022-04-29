package com.gornostai.rickandmorty.ui.screens.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.databinding.FragmentCharactersBinding
import com.gornostai.rickandmorty.ui.screens.characterDetail.CharacterDetailFragment
import com.gornostai.rickandmorty.ui.screens.characters.adapters.CharactersAdapter
import com.gornostai.rickandmorty.utills.Navigator

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var viewModel: CharactersViewModel

    val adapter = CharactersAdapter()

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
        viewModel.charactersList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvCharacters.adapter = adapter
        adapter.onCharacterItemClickListener = {
            Navigator.setFragment(
                CharacterDetailFragment.newInstance(it.id),
                (requireActivity() as AppCompatActivity),
                true
            )
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = CharactersFragment()
    }

}