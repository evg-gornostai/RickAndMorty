package com.gornostai.rickandmorty.presentation.screens.locationDetails

import android.app.Application
import androidx.lifecycle.*
import com.gornostai.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.gornostai.rickandmorty.data.repositories.LocationsRepositoryImpl
import com.gornostai.rickandmorty.domain.entities.CharacterEntity
import com.gornostai.rickandmorty.domain.entities.LocationEntity
import com.gornostai.rickandmorty.domain.usecases.GetCharacterItemUseCase
import com.gornostai.rickandmorty.domain.usecases.GetLocationItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val locationsRepository = LocationsRepositoryImpl(application)
    private val charactersRepository = CharactersRepositoryImpl(application)

    private val getLocationItemUseCase = GetLocationItemUseCase(locationsRepository)
    private val getCharacterItemUseCase = GetCharacterItemUseCase(charactersRepository)

    private val _locationItem = MutableLiveData<LocationEntity>()
    val locationItem: LiveData<LocationEntity>
        get() = _locationItem

    private val _charactersList = MutableLiveData<List<CharacterEntity>>()
    val charactersList: LiveData<List<CharacterEntity>>
        get() = _charactersList

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadData(locationItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val locationItem = getLocationItemUseCase.getLocationItem(locationItemId)
            val charactersList = mutableListOf<CharacterEntity>()
            for (i in locationItem.residents) {
                if (i.isNotEmpty()) {
                    charactersList.add(getCharacterItemUseCase.getCharacterItem(i.toInt()))
                }
            }
            _isLoading.postValue(false)
            _locationItem.postValue(locationItem)
            _charactersList.postValue(charactersList.toList())
        }
    }

}