package com.gornostai.rickandmorty.data.repositories

import com.gornostai.rickandmorty.data.mappers.LocationMapper
import com.gornostai.rickandmorty.data.network.ApiFactory
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.repositories.LocationsRepository

class LocationsRepositoryImpl: LocationsRepository {

    override suspend fun getLocationsList(): List<LocationModel> {
        return ApiFactory.locationService.getLocationsList().results.map {
            LocationMapper().mapDtoToEntity(it)
        }
    }

    override suspend fun getLocationItem(locationItemId: Int): LocationModel {
        val dtoModel = ApiFactory.locationService.getLocation(locationItemId.toString())
        return LocationMapper().mapDtoToEntity(dtoModel)
    }
}