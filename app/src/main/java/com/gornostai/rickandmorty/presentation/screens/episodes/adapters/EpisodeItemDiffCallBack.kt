package com.gornostai.rickandmorty.presentation.screens.episodes.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity

class EpisodeItemDiffCallBack: DiffUtil.ItemCallback<EpisodeEntity>() {

    override fun areItemsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean {
        return oldItem == newItem
    }
}