package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository

object LocationsRepositoryImpl: LocationsRepository {

    override fun getLocationsList(): LiveData<List<LocationModel>> {
        TODO("Not yet implemented")
    }
}