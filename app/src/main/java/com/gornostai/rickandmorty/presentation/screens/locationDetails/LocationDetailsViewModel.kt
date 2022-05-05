package com.gornostai.rickandmorty.presentation.screens.locationDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.models.LocationModel
import com.gornostai.rickandmorty.domain.usecases.GetLocationItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationDetailsViewModel : ViewModel() {

    private val repository = LocationsRepositoryImpl()

    private val getLocationItemUseCase = GetLocationItemUseCase(repository)

    private val _locationItem = MutableLiveData<LocationModel>()
    val locationItem: LiveData<LocationModel>
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