package com.gornostai.rickandmorty.domain.usecases

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

class GetEpisodesListUseCase(private val repository: EpisodesRepository) {

    fun getEpisodesList(): LiveData<List<EpisodeModel>>{
        return repository.getEpisodesList()
    }

}