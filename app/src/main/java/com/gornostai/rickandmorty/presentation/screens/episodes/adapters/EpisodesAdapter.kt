package com.gornostai.rickandmorty.presentation.screens.episodes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gornostai.rickandmorty.databinding.ItemEpisodeBinding
import com.gornostai.rickandmorty.domain.entities.EpisodeEntity

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>() {

    private var data: List<EpisodeEntity> = listOf()

    var onEpisodeItemClickListener: ((EpisodeEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onEpisodeItemClickListener?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<EpisodeEntity>) {
        data = newData
        notifyDataSetChanged()
    }

    class EpisodeViewHolder(val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: EpisodeEntity) {
            binding.apply {
                tvEpisodeName.text = model.name
                tvEpisodeNumber.text = model.episode
                tvAirDate.text = model.air_date
            }
        }
    }
}