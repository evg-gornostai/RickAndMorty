package com.gornostai.rickandmorty.presentation.screens.characters.adapters

import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.ItemChatacterBinding
import com.gornostai.rickandmorty.domain.entities.CharacterEntity

class CharacterViewHolder(val binding: ItemChatacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CharacterEntity) {
        binding.apply {
            ivCharacterImage.setImageResource(R.drawable.ic_characters)
            tvCharacterName.text = model.name
            tvCharacterGender.text = model.gender
            tvCharacterSpecies.text = model.species
            tvCharacterStatus.text = model.status
        }
    }
}