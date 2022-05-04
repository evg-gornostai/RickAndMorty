package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository

object LocationsRepositoryImpl: LocationsRepository {

    private val locationsList = mutableListOf<LocationModel>()

    init {
        for (i in 0..50){
            locationsList.add(
                LocationModel(
                    id = i,
                    name = "Citadel of Ricks$i",
                    type = "Space station_$i",
                    dimension = "unknown_$i"
                )
            )
        }
    }

    override suspend fun getLocationsList(): List<LocationModel> {
        return locationsList.toList()
    }

    override suspend fun getLocationItem(locationItemId: Int): LocationModel {
        return locationsList.find {
            it.id == locationItemId
        } ?: throw RuntimeException("Element with id $locationItemId not found")
    }
}