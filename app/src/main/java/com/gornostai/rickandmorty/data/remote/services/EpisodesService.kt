package com.gornostai.rickandmorty.data.remote.services

import com.gornostai.rickandmorty.data.remote.models.episode.EpisodeDto
import com.gornostai.rickandmorty.data.remote.models.episode.EpisodesListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesService {

    @GET("episode/")
    suspend fun getEpisodesList(
        @Query("page") page: Int = 1
    ): EpisodesListDto

    @GET("episode/")
    suspend fun getFilteredEpisodesList(
        @Query("name") name: String = "",
        @Query("episode") episode: String = ""
    ): Response<EpisodesListDto>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String
    ): EpisodeDto

}