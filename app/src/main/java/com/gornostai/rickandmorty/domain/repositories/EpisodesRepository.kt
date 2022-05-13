package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeFilterEntity

interface EpisodesRepository {

    suspend fun getEpisodesList(): List<EpisodeEntity>

    suspend fun getEpisodeItem(episodeItemId: Int): EpisodeEntity

    suspend fun getFilteredEpisodesList(filter: EpisodeFilterEntity): List<EpisodeEntity>

}