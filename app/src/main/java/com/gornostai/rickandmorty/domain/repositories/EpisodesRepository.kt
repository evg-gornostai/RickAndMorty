package com.gornostai.rickandmorty.domain.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.EpisodeModel

interface EpisodesRepository {

    fun getEpisodesList(): LiveData<List<EpisodeModel>>

    fun getEpisodeItem(episodeItemId: Int): EpisodeModel

}