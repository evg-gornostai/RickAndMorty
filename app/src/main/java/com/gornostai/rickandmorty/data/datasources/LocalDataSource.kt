package com.gornostai.rickandmorty.data.datasources

import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.local.models.LocationDbModel

interface LocalDataSource {

    suspend fun addCharacter(characterDbModel: CharacterDbModel)

    suspend fun getCharacter(characterId: Int): CharacterDbModel?

    suspend fun addEpisode(episodeDbModel: EpisodeDbModel)

    suspend fun getEpisode(episodeId: Int): EpisodeDbModel?

    suspend fun addLocation(locationDbModel: LocationDbModel)

    suspend fun getLocation(locationId: Int): LocationDbModel?

}