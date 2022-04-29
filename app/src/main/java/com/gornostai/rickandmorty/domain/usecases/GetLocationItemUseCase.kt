package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository

class GetLocationItemUseCase(private val repository: LocationsRepository) {

    fun getLocationItem(locationItemId: Int): LocationModel{
        return repository.getLocationItem(locationItemId)
    }

}