package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationsListUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    suspend fun getLocationsList(): List<LocationEntity> {
        return repository.getLocationsList()
    }

}