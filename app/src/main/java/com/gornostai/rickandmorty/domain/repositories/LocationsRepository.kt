package com.gornostai.rickandmorty.domain.repositories

import androidx.lifecycle.LiveData
import com.gornostai.rickandmorty.domain.models.LocationModel

interface LocationsRepository {

    fun getLocationsList(): LiveData<List<LocationModel>>

    fun getLocationItem(locationItemId: Int): LocationModel

}