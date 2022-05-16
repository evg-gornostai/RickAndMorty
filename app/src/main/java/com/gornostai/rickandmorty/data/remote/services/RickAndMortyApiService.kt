package com.gornostai.rickandmorty.data.remote.services

import com.gornostai.rickandmorty.data.remote.models.character.CharacterDto
import com.gornostai.rickandmorty.data.remote.models.character.CharactersListDto
import com.gornostai.rickandmorty.data.remote.models.episode.EpisodeDto
import com.gornostai.rickandmorty.data.remote.models.episode.EpisodesListDto
import com.gornostai.rickandmorty.data.remote.models.location.LocationDto
import com.gornostai.rickandmorty.data.remote.models.location.LocationsListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("character/")
    suspend fun getCharactersList(
        @Query("page") page: Int = 1,
        @Query("name") name: String = "",
        @Query("status") status: String = "",
        @Query("species") species: String = "",
        @Query("type") type: String = "",
        @Query("gender") gender: String = ""
    ): Response<CharactersListDto>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): Response<CharacterDto>

    @GET("character/{arrayOfId}")
    suspend fun getCharactersListByIds(
        @Path("arrayOfId") arrayOfId: String
    ): Response<List<CharacterDto>>

    @GET("episode/")
    suspend fun getEpisodesList(
        @Query("page") page: Int = 1,
        @Query("name") name: String = "",
        @Query("episode") episode: String = ""
    ): Response<EpisodesListDto>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String
    ): Response<EpisodeDto>

    @GET("episode/{arrayOfId}")
    suspend fun getEpisodesListByIds(
        @Path("arrayOfId") arrayOfId: String
    ): Response<List<EpisodeDto>>

    @GET("location/")
    suspend fun getLocationsList(
        @Query("page") page: Int = 1,
        @Query("name") name: String = "",
        @Query("type") type: String = "",
        @Query("dimension") dimension: String = ""
    ): Response<LocationsListDto>

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String
    ): Response<LocationDto>

    @GET("location/{arrayOfId}")
    suspend fun getLocationsListByIds(
        @Path("arrayOfId") arrayOfId: String
    ): Response<List<LocationDto>>

}