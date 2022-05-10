package com.gornostai.rickandmorty.presentation.screens.locations.adapters

import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.databinding.ItemLocationBinding
import com.gornostai.rickandmorty.domain.entities.LocationEntity

class LocationViewHolder(val binding: ItemLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: LocationEntity) {
        binding.apply {
            tvLocationName.text = model.name
            tvLocationType.text = model.type
            tvLocationDimension.text = model.dimension
        }
    }
}