package com.gornostai.rickandmorty.presentation.screens.episodes.adapters

import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.databinding.ItemEpisodeBinding
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity

class EpisodeViewHolder(val binding: ItemEpisodeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: EpisodeEntity) {
        binding.apply {
            tvEpisodeName.text = model.name
            tvEpisodeNumber.text = model.episode
            tvAirDate.text = model.air_date
        }
    }
}