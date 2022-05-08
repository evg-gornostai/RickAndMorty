package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.entities.EpisodeEntity

interface EpisodesRepository {

    suspend fun loadData()

    suspend fun getEpisodesList(): List<EpisodeEntity>

    suspend fun getEpisodeItem(episodeItemId: Int): EpisodeEntity

}