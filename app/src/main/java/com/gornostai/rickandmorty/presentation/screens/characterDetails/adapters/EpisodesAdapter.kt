package com.gornostai.rickandmorty.presentation.screens.characterDetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gornostai.rickandmorty.databinding.ItemEpisodeBinding
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity

class EpisodesAdapter : ListAdapter<EpisodeEntity, EpisodeViewHolder>(EpisodeItemDiffCallBack()) {

    var onEpisodeItemClickListener: ((EpisodeEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onEpisodeItemClickListener?.invoke(getItem(position))
        }
    }

}