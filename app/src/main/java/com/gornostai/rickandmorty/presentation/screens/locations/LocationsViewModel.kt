package com.gornostai.rickandmorty.presentation.screens.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.entities.LocationFilterEntity
import com.gornostai.rickandmorty.domain.usecases.GetLocationsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsListUseCase
) : ViewModel() {

    var filter = LocationFilterEntity()

    private val _locationsList = MutableLiveData<List<LocationEntity>>()
    val locationsList: LiveData<List<LocationEntity>>
        get() = _locationsList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData(filter: LocationFilterEntity = LocationFilterEntity()) {
        this.filter = filter
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _locationsList.postValue(listOf())
            val locations = getLocationsUseCase.getLocationsList(filter)
            _isLoading.postValue(false)
            _locationsList.postValue(locations)
        }
    }

}