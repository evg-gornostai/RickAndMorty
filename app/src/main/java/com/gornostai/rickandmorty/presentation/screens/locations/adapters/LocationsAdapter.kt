package com.gornostai.rickandmorty.presentation.screens.locations.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.databinding.ItemLocationBinding
import com.gornostai.rickandmorty.domain.models.LocationModel

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    private var data: List<LocationModel> = listOf()

    var onLocationItemClickListener: ((LocationModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onLocationItemClickListener?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<LocationModel>) {
        data = newData
        notifyDataSetChanged()
    }

    class LocationViewHolder(val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: LocationModel) {
            binding.apply {
                tvLocationName.text = model.name
                tvLocatoinType.text = model.type
                tvLocationDimension.text = model.dimension
            }
        }
    }
}