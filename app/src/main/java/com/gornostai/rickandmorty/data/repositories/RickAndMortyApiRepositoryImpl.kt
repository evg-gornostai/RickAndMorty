package com.gornostai.rickandmorty.data.repositories

import com.gornostai.rickandmorty.data.datasources.IsOnlineDataSource
import com.gornostai.rickandmorty.data.datasources.LocalDataSource
import com.gornostai.rickandmorty.data.datasources.RemoteDataSource
import com.gornostai.rickandmorty.data.local.dao.RickAndMortyApiDao
import com.gornostai.rickandmorty.data.local.models.CharacterDbModel
import com.gornostai.rickandmorty.data.local.models.EpisodeDbModel
import com.gornostai.rickandmorty.data.mappers.CharacterMapper
import com.gornostai.rickandmorty.data.mappers.EpisodeMapper
import com.gornostai.rickandmorty.data.mappers.LocationMapper
import com.gornostai.rickandmorty.data.remote.services.RickAndMortyApiService
import com.gornostai.rickandmorty.domain.entities.*
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import javax.inject.Inject

class RickAndMortyApiRepositoryImpl @Inject constructor(
    private val isOnlineDataSource: IsOnlineDataSource,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val rickAndMortyApiService: RickAndMortyApiService,
    private val rickAndMortyApiDao: RickAndMortyApiDao
) : RickAndMortyApiRepository {

    override suspend fun getCharacterItem(characterItemId: Int): CharacterEntity? {
        val characterMapper = CharacterMapper()
        val dbModel = localDataSource.getCharacter(characterItemId.toString())
        if (dbModel != null) {
            return characterMapper.mapDbModelToEntity(dbModel)
        } else {
            if (isOnlineDataSource.isOnline()) {
                val dtoModel = remoteDataSource.getCharacter(characterItemId)
                if (dtoModel != null) {
                    val dbModelTemp = characterMapper.mapDtoToDbModel(dtoModel)
                    localDataSource.addCharacter(dbModelTemp)
                    return characterMapper.mapDbModelToEntity(dbModelTemp)
                } else {
                    return null
                }
            } else {
                return null
            }
        }
    }

    override suspend fun getCharactersList(filter: CharacterFilterEntity): List<CharacterEntity> {
        if (isOnlineDataSource.isOnline()) {
            for (p in 1..5) {
                val response = rickAndMortyApiService.getCharactersList(
                    page = p,
                    name = filter.name,
                    status = filter.status,
                    species = filter.species,
                    type = filter.type,
                    gender = filter.gender
                )
                if (response.isSuccessful) {
                    val temp = response.body()?.results?.map {
                        CharacterMapper().mapDtoToDbModel(it)
                    }
                    if (temp != null) {
                        for (i in temp) {
                            localDataSource.addCharacter(i)
                        }
                    }
                }
            }
            val dbModels = rickAndMortyApiDao.getCharactersList(
                name = "%${filter.name}%",
                status = "%${filter.status}%",
                species = "%${filter.species}%",
                type = "%${filter.type}%",
                gender = "%${filter.gender}%"
            )
            return dbModels.map { CharacterMapper().mapDbModelToEntity(it) }
        } else {
            val temp = rickAndMortyApiDao.getCharactersList(
                name = "%${filter.name}%",
                status = "%${filter.status}%",
                species = "%${filter.species}%",
                type = "%${filter.type}%",
                gender = "%${filter.gender}%"
            )
            return temp.map { CharacterMapper().mapDbModelToEntity(it) }
        }
    }

    override suspend fun getCharactersListByIds(arrayOfId: List<String>): List<CharacterEntity> {
        if (isOnlineDataSource.isOnline()) {
            val response = rickAndMortyApiService.getCharactersListByIds(arrayOfId.toString())
            if (response.isSuccessful) {
                val temp = response.body()?.map { CharacterMapper().mapDtoToDbModel(it) }
                if (temp != null) {
                    for (i in temp) {
                        localDataSource.addCharacter(i)
                    }
                    return temp.map { CharacterMapper().mapDbModelToEntity(it) }
                }
            }
        } else {
            val list = mutableListOf<CharacterDbModel>()
            for (i in arrayOfId){
                val temp = localDataSource.getCharacter(i)
                if (temp != null){
                    list.add(temp)
                }
            }
            return list.map { CharacterMapper().mapDbModelToEntity(it) }
        }
        return emptyList()
    }


    override suspend fun getEpisodeItem(episodeItemId: Int): EpisodeEntity? {
        val episodeMapper = EpisodeMapper()
        val dbModel = localDataSource.getEpisode(episodeItemId)
        if (dbModel != null) {
            return episodeMapper.mapDbModelToEntity(dbModel)
        } else {
            if (isOnlineDataSource.isOnline()) {
                val dtoModel = remoteDataSource.getEpisode(episodeItemId)
                if (dtoModel != null) {
                    val dbModelTemp = episodeMapper.mapDtoToDbModel(dtoModel)
                    localDataSource.addEpisode(dbModelTemp)
                    return episodeMapper.mapDbModelToEntity(dbModelTemp)
                } else {
                    return null
                }
            } else {
                return null
            }
        }
    }

    override suspend fun getEpisodesList(filter: EpisodeFilterEntity): List<EpisodeEntity> {
        if (isOnlineDataSource.isOnline()) {
            for (p in 1..5) {
                val response = rickAndMortyApiService.getEpisodesList(
                    page = p,
                    name = filter.name,
                    episode = filter.code
                )
                if (response.isSuccessful) {
                    val temp = response.body()?.results?.map {
                        EpisodeMapper().mapDtoToDbModel(it)
                    }
                    if (temp != null) {
                        for (i in temp) {
                            localDataSource.addEpisode(i)
                        }
                    }
                }
            }
            val dbModels = rickAndMortyApiDao.getEpisodesList(
                name = "%${filter.name}%",
                code = "%${filter.code}%"
            )
            return dbModels.map { EpisodeMapper().mapDbModelToEntity(it) }
        } else {
            val temp = rickAndMortyApiDao.getEpisodesList(
                name = "%${filter.name}%",
                code = "%${filter.code}%"
            )
            return temp.map { EpisodeMapper().mapDbModelToEntity(it) }
        }
    }

    override suspend fun getEpisodesListByIds(arrayOfId: List<String>): List<EpisodeEntity> {
        if (isOnlineDataSource.isOnline()) {
            val response = rickAndMortyApiService.getEpisodesListByIds(arrayOfId.toString())
            if (response.isSuccessful) {
                val temp = response.body()?.map { EpisodeMapper().mapDtoToDbModel(it) }
                if (temp != null) {
                    for (i in temp) {
                        localDataSource.addEpisode(i)
                    }
                    return temp.map { EpisodeMapper().mapDbModelToEntity(it) }
                }
            }
        } else {
            val list = mutableListOf<EpisodeDbModel>()
            for (i in arrayOfId){
                val temp = localDataSource.getEpisode(i.toInt())
                if (temp != null){
                    list.add(temp)
                }
            }
            return list.map { EpisodeMapper().mapDbModelToEntity(it) }
        }
        return emptyList()
    }

    override suspend fun getLocationItem(locationItemId: Int): LocationEntity? {
        val locationMapper = LocationMapper()
        val dbModel = localDataSource.getLocation(locationItemId)
        if (dbModel != null) {
            return locationMapper.mapDbModelToEntity(dbModel)
        } else {
            if (isOnlineDataSource.isOnline()) {
                val dtoModel = remoteDataSource.getLocation(locationItemId)
                if (dtoModel != null) {
                    val dbModelTemp = locationMapper.mapDtoToDbModel(dtoModel)
                    localDataSource.addLocation(dbModelTemp)
                    return locationMapper.mapDbModelToEntity(dbModelTemp)
                } else {
                    return null
                }
            } else {
                return null
            }
        }
    }

    override suspend fun getLocationsList(filter: LocationFilterEntity): List<LocationEntity> {
        if (isOnlineDataSource.isOnline()) {
            for (p in 1..5) {
                val response = rickAndMortyApiService.getLocationsList(
                    page = p,
                    name = filter.name,
                    dimension = filter.dimension,
                    type = filter.type
                )
                if (response.isSuccessful) {
                    val temp = response.body()?.results?.map {
                        LocationMapper().mapDtoToDbModel(it)
                    }
                    if (temp != null) {
                        for (i in temp) {
                            localDataSource.addLocation(i)
                        }
                    }
                }
            }
            val dbModels = rickAndMortyApiDao.getLocationsList(
                name = "%${filter.name}%",
                type = "%${filter.type}%",
                dimension = "%${filter.dimension}%"
            )
            return dbModels.map { LocationMapper().mapDbModelToEntity(it) }
        } else {
            val temp = rickAndMortyApiDao.getLocationsList(
                name = "%${filter.name}%",
                type = "%${filter.type}%",
                dimension = "%${filter.dimension}%"
            )
            return temp.map { LocationMapper().mapDbModelToEntity(it) }
        }
    }

    override suspend fun getLocationsListByIds(arrayOfId: String): List<LocationEntity> {
        return emptyList()
    }
}