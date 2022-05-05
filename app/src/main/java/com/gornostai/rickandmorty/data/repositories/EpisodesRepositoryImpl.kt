package com.gornostai.rickandmorty.data.repositories

import com.gornostai.rickandmorty.domain.mappers.EpisodeMapper
import com.gornostai.rickandmorty.data.network.ApiFactory
import com.gornostai.rickandmorty.domain.models.EpisodeModel
import com.gornostai.rickandmorty.domain.repositories.EpisodesRepository

class EpisodesRepositoryImpl : EpisodesRepository {

    override suspend fun getEpisodesList(): List<EpisodeModel> {
        return ApiFactory.episodeService.getEpisodesList().results.map {
            EpisodeMapper().mapDtoToEntity(it)
        }
    }

    override suspend fun getEpisodeItem(episodeItemId: Int): EpisodeModel {
        val dtoModel = ApiFactory.episodeService.getEpisode(episodeItemId.toString())
        return EpisodeMapper().mapDtoToEntity(dtoModel)
    }
}