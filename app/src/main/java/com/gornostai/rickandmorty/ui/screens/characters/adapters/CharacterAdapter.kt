package com.gornostai.rickandmorty.ui.screens.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.ItemChatacterBinding

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var data: List<CharacterItemModel> = listOf(
        CharacterItemModel(id = 1, name = "rick morty1", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 2, name = "rick morty2", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 3, name = "rick morty3", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 4, name = "rick morty4", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 5, name = "rick morty5", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 6, name = "rick morty6", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 7, name = "rick morty7", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 8, name = "rick morty8", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 9, name = "rick morty9", species = "human", status = "alive", gender = "man", image = ""),
        CharacterItemModel(id = 10, name = "rick morty10", species = "human", status = "alive", gender = "man", image = ""),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemChatacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<CharacterItemModel>){
        data = newData
    }

    class CharacterViewHolder(val binding: ItemChatacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CharacterItemModel){
            binding.apply {
                ivCharacterImage.setImageResource(R.drawable.icon_characters)
                tvCharacterName.text = model.name
                tvCharacterGender.text = model.gender
                tvCharacterSpecies.text = model.species
                tvCharacterStatus.text = model.status
            }
        }
    }
}













