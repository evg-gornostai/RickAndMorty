package com.gornostai.rickandmorty.presentation.screens.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.gornostai.rickandmorty.presentation.contracts.HasBackButton
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle

class CharacterDetailsFragment : Fragment(), HasCustomTitle, HasBackButton {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private lateinit var viewModel: CharacterDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterDetailsViewModel::class.java]
        setupLoading()
        setupData()
    }

    override fun getTitleRes(): Int = R.string.character_details_title

    private fun setupData() {
        viewModel.characterItem.observe(viewLifecycleOwner) {
            binding.tvtest.text = it.toString()
        }
        val id = arguments.let {
            it?.getInt(ARG_CHARACTER_ITEM_ID)
        }
        viewModel.getCharacterItem(id ?: throw RuntimeException("id is null"))
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

        const val ARG_CHARACTER_ITEM_ID = "ARG_CHARACTER_ITEM_ID"

        fun newInstance(characterItemId: Int) =
            CharacterDetailsFragment().apply {
                arguments = bundleOf(ARG_CHARACTER_ITEM_ID to characterItemId)
            }
    }

}