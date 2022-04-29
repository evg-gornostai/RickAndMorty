package com.gornostai.rickandmorty.ui.screens.locations

import androidx.lifecycle.ViewModel
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.usecases.GetLocationsListUseCase

class LocationsViewModel: ViewModel() {

    private val repository = LocationsRepositoryImpl

    private val getLocationsUseCase = GetLocationsListUseCase(repository)

    val locationsList = getLocationsUseCase.getLocationsList()

}