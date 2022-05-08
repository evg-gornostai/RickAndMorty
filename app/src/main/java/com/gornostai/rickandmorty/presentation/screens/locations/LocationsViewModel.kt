package com.gornostai.rickandmorty.presentation.screens.locations

import android.app.Application
import androidx.lifecycle.*
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.usecases.GetLocationsListUseCase
import com.gornostai.rickandmorty.domain.usecases.LoadLocationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel(application: Application) : AndroidViewModel(application){

    private val repository = LocationsRepositoryImpl(application)

    private val getLocationsUseCase = GetLocationsListUseCase(repository)
    private val loadLocationsUseCase = LoadLocationsUseCase(repository)

    private val _locationsList = MutableLiveData<List<LocationEntity>>()
    val locationsList: LiveData<List<LocationEntity>>
        get() = _locationsList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            var locations = getLocationsUseCase.getLocationsList()
            if (locations.isEmpty()){
                loadLocationsUseCase.loadData()
                locations = getLocationsUseCase.getLocationsList()
            }
            _isLoading.postValue(false)
            _locationsList.postValue(locations)
        }
    }

}