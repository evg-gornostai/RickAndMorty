package com.gornostai.rickandmorty.domain.repositories

import com.gornostai.rickandmorty.domain.models.LocationModel

interface LocationsRepository {

    suspend fun getLocationsList(): List<LocationModel>

    suspend fun getLocationItem(locationItemId: Int): LocationModel

}