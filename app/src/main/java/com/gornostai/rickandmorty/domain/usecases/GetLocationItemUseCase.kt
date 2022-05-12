package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationItemUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    suspend fun getLocationItem(locationItemId: Int): LocationEntity {
        return repository.getLocationItem(locationItemId)
    }

}