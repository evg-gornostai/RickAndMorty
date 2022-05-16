package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.entities.*

interface RickAndMortyApiRepository {

    suspend fun getCharacterItem(characterItemId: Int): CharacterEntity?

    suspend fun getCharactersList(filter: CharacterFilterEntity): List<CharacterEntity>

    suspend fun getCharactersListByIds(arrayOfId: String): List<CharacterEntity>

    suspend fun getEpisodeItem(episodeItemId: Int): EpisodeEntity?

    suspend fun getEpisodesList(filter: EpisodeFilterEntity): List<EpisodeEntity>

    suspend fun getEpisodesListByIds(arrayOfId: String): List<EpisodeEntity>

    suspend fun getLocationItem(locationItemId: Int): LocationEntity?

    suspend fun getLocationsList(filter: LocationFilterEntity): List<LocationEntity>

    suspend fun getLocationsListByIds(arrayOfId: String): List<LocationEntity>

}