package com.gornostai.rickandmorty.data.datasources

import com.gornostai.rickandmorty.data.local.dao.RickAndMortyApiDao
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.local.models.LocationDbModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val rickAndMortyApiDao: RickAndMortyApiDao
) : LocalDataSource {

    override suspend fun addCharacter(characterDbModel: CharacterDbModel) {
        rickAndMortyApiDao.addCharacter(characterDbModel)
    }

    override suspend fun getCharacter(characterId: String): CharacterDbModel? {
        return rickAndMortyApiDao.getCharacter(characterId)
    }

    override suspend fun addEpisode(episodeDbModel: EpisodeDbModel) {
        rickAndMortyApiDao.addEpisode(episodeDbModel)
    }

    override suspend fun getEpisode(episodeId: Int): EpisodeDbModel? {
        return rickAndMortyApiDao.getEpisode(episodeId)
    }

    override suspend fun addLocation(locationDbModel: LocationDbModel) {
        rickAndMortyApiDao.addLocation(locationDbModel)
    }

    override suspend fun getLocation(locationId: Int): LocationDbModel? {
        return rickAndMortyApiDao.getLocation(locationId)
    }
}