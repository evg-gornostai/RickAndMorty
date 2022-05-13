package com.gornostai.rickandmorty.data.remote.services

import com.gornostai.rickandmorty.data.remote.models.character.CharacterDto
import com.gornostai.rickandmorty.data.remote.models.character.CharactersListDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("character/")
    suspend fun getCharactersList(
        @Query("page") page: Int = 1
    ): CharactersListDto

    @GET("character/")
    suspend fun getFilteredCharactersList(
        @Query("name") name: String = "",
        @Query("status") status: String = "",
        @Query("species") species: String = "",
        @Query("type") type: String = "",
        @Query("gender") gender: String = ""
    ): Response<CharactersListDto>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): CharacterDto

}