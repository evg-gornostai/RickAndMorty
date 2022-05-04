package com.gornostai.rickandmorty.domain.usecases

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository

class GetLocationsListUseCase(private val repository: LocationsRepository) {

    suspend fun getLocationsList(): List<LocationModel>{
        return repository.getLocationsList()
    }

}