package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

object EpisodesRepositoryImpl: EpisodesRepository {

    override fun getEpisodesList(): LiveData<List<EpisodeModel>> {
        TODO("Not yet implemented")
    }
}