package com.gornostai.rickandmorty.ui.screens.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.ItemChatacterBinding
import com.gornostai.rickandmorty.domain.models.CharacterModel

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private var data: List<CharacterModel> = listOf()

    var onCharacterItemClickListener: ((CharacterModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemChatacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onCharacterItemClickListener?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<CharacterModel>) {
        data = newData
        notifyDataSetChanged()
    }

    class CharacterViewHolder(val binding: ItemChatacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CharacterModel) {
            binding.apply {
                ivCharacterImage.setImageResource(R.drawable.ic_characters)
                tvCharacterName.text = model.name
                tvCharacterGender.text = model.gender
                tvCharacterSpecies.text = model.species
                tvCharacterStatus.text = model.status
            }
        }
    }
}













