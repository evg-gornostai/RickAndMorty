package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.entities.LocationFilterEntity
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetFilteredLocationListUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    suspend fun getFilteredLocationsList(filter: LocationFilterEntity): List<LocationEntity> {
        return repository.getFilteredLocationsList(filter)
    }

}