package com.gornostai.rickandmorty.presentation.screens.locationDetails.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gornostai.rickandmorty.domain.entities.CharacterEntity

class CharacterItemDiffCallBack: DiffUtil.ItemCallback<CharacterEntity>() {

    override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem == newItem
    }
}