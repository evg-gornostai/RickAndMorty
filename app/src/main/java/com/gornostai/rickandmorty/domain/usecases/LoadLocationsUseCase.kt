package com.gornostai.rickandmorty.domain.usecases

import com.gornostai.rickandmorty.domain.repositories.LocationsRepository
import javax.inject.Inject

class LoadLocationsUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    suspend fun loadData() {
        repository.loadData()
    }

}