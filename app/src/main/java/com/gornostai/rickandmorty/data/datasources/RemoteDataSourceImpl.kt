package com.gornostai.rickandmorty.data.datasources

import com.gornostai.rickandmorty.data.remote.models.character.CharacterDto
import com.gornostai.rickandmorty.data.remote.models.episode.EpisodeDto
import com.gornostai.rickandmorty.data.remote.models.location.LocationDto
import com.gornostai.rickandmorty.data.remote.services.RickAndMortyApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val rickAndMortyApiService: RickAndMortyApiService,
) : RemoteDataSource {

    override suspend fun getCharacter(id: Int): CharacterDto? {
        val response = rickAndMortyApiService.getCharacter(id.toString())
        if (response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }

    override suspend fun getEpisode(id: Int): EpisodeDto? {
        val response = rickAndMortyApiService.getEpisode(id.toString())
        if (response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }

    override suspend fun getLocation(id: Int): LocationDto? {
        val response = rickAndMortyApiService.getLocation(id.toString())
        if (response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }
}