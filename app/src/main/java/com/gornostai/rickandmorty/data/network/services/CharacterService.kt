package com.gornostai.rickandmorty.data.network.services

import com.gornostai.rickandmorty.data.network.models.character.CharacterDto
import com.gornostai.rickandmorty.data.network.models.character.CharactersListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getCharactersList(): CharactersListDto

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): CharacterDto

}