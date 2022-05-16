package com.gornostai.rickandmorty.data.datasources

import com.gornostai.rickandmorty.data.remote.models.character.CharacterDto
import com.gornostai.rickandmorty.data.remote.models.episode.EpisodeDto
import com.gornostai.rickandmorty.data.remote.models.location.LocationDto

interface RemoteDataSource {

    suspend fun getCharacter(id: Int): CharacterDto?

    suspend fun getEpisode(id: Int): EpisodeDto?

    suspend fun getLocation(id: Int): LocationDto?

}