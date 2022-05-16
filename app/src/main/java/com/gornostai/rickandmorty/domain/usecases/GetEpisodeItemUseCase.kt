package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.EpisodeEntity
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import javax.inject.Inject

class GetEpisodeItemUseCase @Inject constructor(
    private val repository: RickAndMortyApiRepository
) {

    suspend fun getEpisodeItem(episodeItemId: Int): EpisodeEntity? {
        return repository.getEpisodeItem(episodeItemId)
    }

}