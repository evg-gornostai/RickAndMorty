package com.gornostai.rickandmorty.ui.screens.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gornostai.rickandmorty.databinding.FragmentCharactersBinding
import com.gornostai.rickandmorty.ui.screens.characters.adapters.CharactersAdapter

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding

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
        binding.rvCharacters.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() = CharactersFragment()
    }

}