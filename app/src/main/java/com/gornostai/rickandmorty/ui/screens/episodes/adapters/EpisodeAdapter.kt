package com.gornostai.rickandmorty.ui.screens.episodes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.databinding.ItemEpisodeBinding

class EpisodeAdapter: RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private var data: List<EpisodeItemModel> = listOf(
        EpisodeItemModel(id = 0, name = "pilot1", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 1, name = "pilot2", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 2, name = "pilot3", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 3, name = "pilot4", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 4, name = "pilot5", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 5, name = "pilot6", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 6, name = "pilot7", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 7, name = "pilot8", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 8, name = "pilot9", episode = "S01E03", air_date = "December 16, 2013"),
        EpisodeItemModel(id = 9, name = "pilot10", episode = "S01E03", air_date = "December 16, 2013"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<EpisodeItemModel>){
        data = newData
    }

    class EpisodeViewHolder(val binding: ItemEpisodeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: EpisodeItemModel){
            binding.apply {
                tvEpisodeName.text = model.name
                tvEpisodeNumber.text = model.episode
                tvAirDate.text = model.air_date
            }
        }
    }
}