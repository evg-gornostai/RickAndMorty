package com.gornostai.rickandmorty.presentation.screens.locationDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharactersListUseCase
import com.gornostai.rickandmorty.domain.usecases.GetLocationItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailsViewModel @Inject constructor(
    private val getLocationItemUseCase: GetLocationItemUseCase,
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    private val _locationItem = MutableLiveData<LocationEntity>()
    val locationItem: LiveData<LocationEntity>
        get() = _locationItem

    private val _charactersList = MutableLiveData<List<CharacterEntity>>()
    val charactersList: LiveData<List<CharacterEntity>>
        get() = _charactersList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _showErrorMessage = MutableLiveData<Boolean>().apply { value = false }
    val showErrorMessage: LiveData<Boolean>
        get() = _showErrorMessage

    fun loadData(locationItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val locationItem = getLocationItemUseCase.getLocationItem(locationItemId)
            val charactersList = mutableListOf<CharacterEntity>()
            if (locationItem != null) {
                if (locationItem.residents.isNotEmpty()) {
                    val arrayOfIds = locationItem.residents
                    charactersList.addAll(getCharactersListUseCase.getCharactersListByIds(arrayOfIds))
                }
            }
            _isLoading.postValue(false)
            if (locationItem != null) {
                _locationItem.postValue(locationItem!!)
                _charactersList.postValue(charactersList.toList())
            } else {
                _showErrorMessage.postValue(true)
                _showErrorMessage.postValue(false)
            }
        }
    }

}