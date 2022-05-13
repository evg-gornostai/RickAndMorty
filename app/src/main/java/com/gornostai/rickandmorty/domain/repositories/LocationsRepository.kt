package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.entities.LocationFilterEntity

interface LocationsRepository {

    suspend fun getLocationsList(): List<LocationEntity>

    suspend fun getLocationItem(locationItemId: Int): LocationEntity

    suspend fun getFilteredLocationsList(filter: LocationFilterEntity): List<LocationEntity>

}