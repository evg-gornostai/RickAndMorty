package com.gornostai.rickandmorty.presentation.screens.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.entities.LocationFilterEntity
import com.gornostai.rickandmorty.domain.usecases.GetFilteredLocationListUseCase
import com.gornostai.rickandmorty.domain.usecases.GetLocationsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsListUseCase,
    private val getFilteredLocationListUseCase: GetFilteredLocationListUseCase
) : ViewModel() {

    var filter = LocationFilterEntity()

    private val _locationsList = MutableLiveData<List<LocationEntity>>()
    val locationsList: LiveData<List<LocationEntity>>
        get() = _locationsList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        filter = LocationFilterEntity()
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val locations = getLocationsUseCase.getLocationsList()
            _isLoading.postValue(false)
            _locationsList.postValue(locations)
        }
    }

    fun getFilteredData(filter: LocationFilterEntity) {
        this.filter = filter
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val filteredLocations =
                getFilteredLocationListUseCase.getFilteredLocationsList(filter)
            _isLoading.postValue(false)
            _locationsList.postValue(filteredLocations)
        }
    }

}