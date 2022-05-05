package com.gornostai.rickandmorty.data.network.services

import com.gornostai.rickandmorty.data.network.models.episode.EpisodeDto
import com.gornostai.rickandmorty.data.network.models.episode.EpisodesListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeService {

    @GET("episode")
    suspend fun getEpisodesList(): EpisodesListDto

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String
    ): EpisodeDto

}