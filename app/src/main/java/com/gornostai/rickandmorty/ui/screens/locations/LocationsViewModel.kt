package com.gornostai.rickandmorty.ui.screens.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.usecases.GetLocationsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel : ViewModel() {

    private val repository = LocationsRepositoryImpl

    private val getLocationsUseCase = GetLocationsListUseCase(repository)

    private val _locationsList = MutableLiveData<List<LocationModel>>()
    val locationsList: LiveData<List<LocationModel>>
        get() = _locationsList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val locations = getLocationsUseCase.getLocationsList()
            _isLoading.postValue(false)
            _locationsList.postValue(locations)
        }
    }

}