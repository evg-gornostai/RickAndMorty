package com.gornostai.rickandmorty.ui.screens.locations.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.databinding.ItemLocationBinding

class LocationsAdapter: RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    private var data: List<LocationItemModel> = listOf(
        LocationItemModel(id = 0, name = "Earth1", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 1, name = "Earth2", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 2, name = "Earth3", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 3, name = "Earth4", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 4, name = "Earth5", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 5, name = "Earth6", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 6, name = "Earth7", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 7, name = "Earth8", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 8, name = "Earth9", type = "Planet", dimension = "Dimension C-137"),
        LocationItemModel(id = 9, name = "Earth10", type = "Planet", dimension = "Dimension C-137"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<LocationItemModel>){
        data = newData
    }

    class LocationViewHolder(val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: LocationItemModel){
            binding.apply {
                tvLocationName.text = model.name
                tvLocatoinType.text = model.type
                tvLocationDimension.text = model.dimension
            }
        }
    }
}