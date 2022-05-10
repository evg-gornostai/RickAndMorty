package com.gornostai.rickandmorty.presentation.screens.episodeDetails.adapters

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.gornostai.rickandmorty.databinding.ItemChatacterBinding
import com.gornostai.rickandmorty.domain.entities.CharacterEntity

class CharacterViewHolder(val binding: ItemChatacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CharacterEntity) {
        binding.apply {
            val circularProgressDrawable = CircularProgressDrawable(root.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide
                .with(root.context)
                .load(model.image)
                .placeholder(circularProgressDrawable)
                .into(ivCharacterImage)
            tvCharacterName.text = model.name
            tvCharacterGender.text = model.gender
            tvCharacterSpecies.text = model.species
            tvCharacterStatus.text = model.status
            if (model.status == "Alive") {
                tvCharacterStatus.setTextColor(Color.GREEN)
            } else {
                tvCharacterStatus.setTextColor(Color.RED)
            }
        }
    }
}