package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.entities.LocationFilterEntity
import com.gornostai.rickandmorty.domain.repositories.RickAndMortyApiRepository
import javax.inject.Inject

class GetLocationsListUseCase @Inject constructor(
    private val repository: RickAndMortyApiRepository
) {

    suspend fun getLocationsList(filter: LocationFilterEntity): List<LocationEntity> {
        return repository.getLocationsList(filter)
    }

    suspend fun getLocationsListByIds(arrayOfId: String): List<LocationEntity> {
        return repository.getLocationsListByIds(arrayOfId)
    }

}