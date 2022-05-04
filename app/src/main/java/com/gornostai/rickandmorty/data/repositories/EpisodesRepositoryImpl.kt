package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

object EpisodesRepositoryImpl : EpisodesRepository {

    private val episodesList = mutableListOf<EpisodeModel>()

    init {
        for (i in 0..50) {
            episodesList.add(
                EpisodeModel(
                    id = i,
                    name = "pilot$i",
                    air_date = "December 2, 2013_$i",
                    episode = "S01E01_$i"
                )
            )
        }
    }

    override suspend fun getEpisodesList(): List<EpisodeModel> {
        return episodesList.toList()
    }

    override suspend fun getEpisodeItem(episodeItemId: Int): EpisodeModel {
        return episodesList.find {
            it.id == episodeItemId
        } ?: throw RuntimeException("Element with id $episodeItemId not found")
    }
}