package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.repositories.LocationsRepository

class LoadLocationsUseCase(private val repository: LocationsRepository) {

    suspend fun loadData() {
        repository.loadData()
    }

}