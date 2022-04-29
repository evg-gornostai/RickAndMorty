package com.gornostai.rickandmorty.ui.screens.locationDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.usecases.GetLocationItemUseCase

class LocationDetailsViewModel : ViewModel() {

    private val repository = LocationsRepositoryImpl

    private val getLocationItemUseCase = GetLocationItemUseCase(repository)

    private val _locationItem = MutableLiveData<LocationModel>()
    val locationItem: LiveData<LocationModel>
        get() = _locationItem

    fun getLocationItem(locationInemId: Int) {
        val item = getLocationItemUseCase.getLocationItem(locationInemId)
        _locationItem.value = item
    }

}