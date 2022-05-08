package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

class GetEpisodeItemUseCase(private val repository: EpisodesRepository) {

    suspend fun getEpisodeItem(episodeItemId: Int): EpisodeEntity{
        return repository.getEpisodeItem(episodeItemId)
    }

}