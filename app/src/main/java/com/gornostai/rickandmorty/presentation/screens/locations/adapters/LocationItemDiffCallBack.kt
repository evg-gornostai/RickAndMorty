package com.gornostai.rickandmorty.presentation.screens.locations.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gornostai.rickandmorty.domain.entities.LocationEntity

class LocationItemDiffCallBack: DiffUtil.ItemCallback<LocationEntity>() {

    override fun areItemsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
        return oldItem == newItem
    }
}