package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.entities.EpisodeFilterEntity
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import javax.inject.Inject

class GetEpisodesListUseCase @Inject constructor(
    private val repository: RickAndMortyApiRepository
) {

    suspend fun getEpisodesList(filter: EpisodeFilterEntity): List<EpisodeEntity> {
        return repository.getEpisodesList(filter)
    }

    suspend fun getEpisodesListByIds(arrayOfId: String): List<EpisodeEntity> {
        return repository.getEpisodesListByIds(arrayOfId)
    }

}