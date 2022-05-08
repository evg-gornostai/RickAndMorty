package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.entities.LocationEntity

interface LocationsRepository {

    suspend fun loadData()

    suspend fun getLocationsList(): List<LocationEntity>

    suspend fun getLocationItem(locationItemId: Int): LocationEntity

}