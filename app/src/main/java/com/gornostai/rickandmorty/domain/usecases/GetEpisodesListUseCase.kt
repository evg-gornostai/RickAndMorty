package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodesListUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend fun getEpisodesList(): List<EpisodeEntity> {
        return repository.getEpisodesList()
    }

}