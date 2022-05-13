package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeFilterEntity
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetFilteredEpisodesListUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend fun getFilteredEpisodesList(filter: EpisodeFilterEntity): List<EpisodeEntity> {
        return repository.getFilteredEpisodesList(filter)
    }

}