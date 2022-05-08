package com.gornostai.rickandmorty.presentation.screens.locationDetails

import android.app.Application
import androidx.lifecycle.*
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.usecases.GetLocationItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LocationsRepositoryImpl(application)

    private val getLocationItemUseCase = GetLocationItemUseCase(repository)

    private val _locationItem = MutableLiveData<LocationEntity>()
    val locationItem: LiveData<LocationEntity>
        get() = _locationItem

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getLocationItem(locationItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val item = getLocationItemUseCase.getLocationItem(locationItemId)
            _isLoading.postValue(false)
            _locationItem.postValue(item)
        }
    }

}