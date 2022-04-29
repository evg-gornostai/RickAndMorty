package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

object EpisodesRepositoryImpl: EpisodesRepository {

    private val episodeListLD = MutableLiveData<List<EpisodeModel>>()
    private val episodeList = mutableListOf<EpisodeModel>()

    init {
        for (i in 0..50){
            episodeList.add(
                EpisodeModel(
                    id = i,
                    name = "pilot$i",
                    air_date = "December 2, 2013_$i",
                    episode = "S01E01_$i"
                )
            )
        }
    }

    override fun getEpisodesList(): LiveData<List<EpisodeModel>> {
        episodeListLD.value = episodeList
        return episodeListLD
    }
}