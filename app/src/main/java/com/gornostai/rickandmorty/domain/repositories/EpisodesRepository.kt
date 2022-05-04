package com.gornostai.rickandmorty.domain.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.EpisodeModel

interface EpisodesRepository {

    suspend fun getEpisodesList(): List<EpisodeModel>

    suspend fun getEpisodeItem(episodeItemId: Int): EpisodeModel

}