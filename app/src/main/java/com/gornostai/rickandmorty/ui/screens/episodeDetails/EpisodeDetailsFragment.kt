package com.gornostai.rickandmorty.ui.screens.episodeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gornostai.rickandmorty.databinding.FragmentEpisodeDetailsBinding

class EpisodeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeDetailsBinding

    private lateinit var viewModel: EpisodeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EpisodeDetailsViewModel::class.java]
        initView()
    }

    private fun initView() {
        val id = arguments.let {
            it?.getInt(ARG_EPISODE_ITEM_ID)
        }
        viewModel.getEpisodeItem(id ?: throw RuntimeException("id is null"))
        viewModel.episodeItem.observe(viewLifecycleOwner) {
            binding.tvtest.text = it.toString()
        }
    }

    companion object {

        const val ARG_EPISODE_ITEM_ID = "ARG_EPISODE_ITEM_ID"

        fun newInstance(episodeItemId: Int) =
            EpisodeDetailsFragment().apply {
                arguments = bundleOf(ARG_EPISODE_ITEM_ID to episodeItemId)
            }
    }

}