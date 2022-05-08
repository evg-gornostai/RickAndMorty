package com.gornostai.rickandmorty.presentation.screens.locations.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gornostai.rickandmorty.databinding.ItemLocationBinding
import com.gornostai.rickandmorty.domain.entities.LocationEntity

class LocationsAdapter :
    ListAdapter<LocationEntity, LocationViewHolder>(LocationItemDiffCallBack()) {

    var onLocationItemClickListener: ((LocationEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onLocationItemClickListener?.invoke(getItem(position))
        }
    }

}