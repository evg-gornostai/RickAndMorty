package com.gornostai.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository

object LocationsRepositoryImpl: LocationsRepository {

    private val locationListLD = MutableLiveData<List<LocationModel>>()
    private val locationList = mutableListOf<LocationModel>()

    init {
        for (i in 0..50){
            locationList.add(
                LocationModel(
                    id = i,
                    name = "Citadel of Ricks$i",
                    type = "Space station_$i",
                    dimension = "unknown_$i"
                )
            )
        }
    }

    override fun getLocationsList(): LiveData<List<LocationModel>> {
        locationListLD.value = locationList
        return locationListLD
    }

    override fun getLocationItem(locationItemId: Int): LocationModel {
        return locationList.find {
            it.id == locationItemId
        } ?: throw RuntimeException("Element with id $locationItemId not found")
    }
}