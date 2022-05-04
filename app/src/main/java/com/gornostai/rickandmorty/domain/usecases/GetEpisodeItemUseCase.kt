package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

class GetEpisodeItemUseCase(private val repository: EpisodesRepository) {

    suspend fun getEpisodeItem(episodeItemId: Int): EpisodeModel{
        return repository.getEpisodeItem(episodeItemId)
    }

}