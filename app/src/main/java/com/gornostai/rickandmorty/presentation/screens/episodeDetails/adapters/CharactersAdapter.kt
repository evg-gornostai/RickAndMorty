package com.gornostai.rickandmorty.presentation.screens.episodeDetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gornostai.rickandmorty.databinding.ItemChatacterBinding
import com.gornostai.rickandmorty.domain.entities.CharacterEntity

class CharactersAdapter :
    ListAdapter<CharacterEntity, CharacterViewHolder>(CharacterItemDiffCallBack()) {

    var onCharacterItemClickListener: ((CharacterEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemChatacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onCharacterItemClickListener?.invoke(getItem(position))
        }
    }

}













